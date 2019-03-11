package com.wu.controller;


import com.wu.pojo.*;
import com.wu.service.*;
import com.wu.util.JsonUtil;
import com.wu.util.PageBean;
import com.wu.util.RedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

@Controller
public class HotelController {
    private static final Jedis jedis = new Jedis("localhost");

    static {
        jedis.auth("511300");
    }

    @Resource
    private OrderService orderService;

    @Resource
    private HouseService houseService;
    @Resource
    private StreetService streetService;

    @Resource
    private BedTypeService bedTypeService;

    @Resource
    private HotelService hotelService;

    @Resource
    private RegionService regionService;

    @RequestMapping(value = "/getRegion")
    @ResponseBody
    public String getRegion() {
        return JsonUtil.getJson(regionService.findRegion());
    }

    @RequestMapping(value = "/chooseStreet")
    @ResponseBody
    public String findStreet(@RequestParam("regionId") Integer regionId) {
        return JsonUtil.getJson(streetService.findStreetByRegion(regionId));
    }

    @RequestMapping(value = "/moreHotel/{regionId}/{checkIn}/{checkOut}")
    public ModelAndView findStreetByRegion(@PathVariable(value = "regionId") String beforeRegionId,
                                           @PathVariable(value = "checkIn") String beforeCheckIn,
                                           @PathVariable(value = "checkOut") String beforeCheckOut,
                                           ModelAndView model) {
        if (beforeRegionId == null) {
            beforeRegionId = "MQ==";
        }
        System.out.println("checkIn解码前:"+beforeCheckIn);
        byte[] regionIdByte = Base64.getDecoder().decode(beforeRegionId.getBytes());
        byte[] checkInByte = Base64.getDecoder().decode(beforeCheckIn.getBytes());
        byte[] checkOutByte = Base64.getDecoder().decode(beforeCheckOut.getBytes());
        String regionIdStr = null;
        String checkIn = null;
        String checkOut = null;
        try {
           String regionIdBase64 = new String(regionIdByte, "utf-8");
            String checkInBase64 = new String(checkInByte, "utf-8");
            String  checkOutBase64 = new String(checkOutByte, "utf-8");

            regionIdStr = URLDecoder.decode(regionIdBase64, "utf-8");
            checkIn = URLDecoder.decode(checkInBase64, "utf-8");
            checkOut = URLDecoder.decode(checkOutBase64, "utf-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("checkIn解码后:"+beforeCheckIn);
        int regionId = Integer.parseInt(regionIdStr);
        List<Region> regions = regionService.findRegion();
        Region region = regionService.findRegionById(regionId);
        List<Street> streets = streetService.findStreetByRegion(regionId);
        model.addObject("streets", streets);
        model.addObject("regions", regions);
        model.addObject("region", region);
        model.addObject("checkIn", checkIn);
        model.addObject("checkOut", checkOut);
        model.setViewName("morehotel");
        return model;
    }


    @RequestMapping(value = "/showHotelByPage")
    @ResponseBody
    public String showHotelByPage(@RequestParam(value = "orderBy", required = false) String orderBy,
                                  @RequestParam(value = "region", required = false) Integer region,
                                  @RequestParam(value = "street", required = false) Integer street,
                                  @RequestParam(value = "price", required = false) Integer price,
                                  @RequestParam(value = "star", required = false) Integer star,
                                  @RequestParam(value = "style", required = false) Integer style,
                                  @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
                                  HttpSession session) {
        Hotel hotel = new Hotel();
        hotel.setRegionId(region);
        hotel.setStreetId(street);
        hotel.setFloorPriceId(price);
        hotel.setStar(star);
        hotel.setStyleId(style);

        PageBean<Hotel> pageBean = new PageBean<>();
        //设置送条数
        pageBean.setTotalCount(hotelService.findHotelCount(hotel));
        //设置页面容量，并且自动设置总页数
        pageBean.setPageSize(2);
        if (pageIndex == null || pageIndex == 0) {
            pageIndex = 1;
        }
        if (pageIndex > pageBean.getTotalPage()) {
            pageIndex = pageBean.getTotalPage();
        }

        //设置当前页码
        pageBean.setPageIndex(pageIndex);

        //设置起始行
        int startRow = pageBean.getStartRow(pageBean.getPageIndex());

        hotel.setStartRow(startRow);
        hotel.setPageSize(pageBean.getPageSize());
        List<Hotel> hotels = null;
        if ("asc".equals(orderBy)) {
            session.removeAttribute("desc");
        }
        if ("desc".equals(orderBy)) {
            session.setAttribute("desc", "desc");
        }

        if (session.getAttribute("desc") != null || "desc".equals(orderBy)) {
            //降序排序
            hotels = hotelService.findHotelByDesc(hotel);
            //设置酒店集合
            pageBean.setList(hotels);
            return JsonUtil.getJson(pageBean);
        } else {
            //升序排序
            hotels = hotelService.getHotelByAsc(hotel);
        }

        //设置酒店集合
        pageBean.setList(hotels);
        return JsonUtil.getJson(pageBean);
    }

    @RequestMapping(value = "/findHouse")
    @ResponseBody
    public String findHouse(@RequestParam(value = "hotelId", required = false) Integer hotelId,
                            @RequestParam(value = "bedTypeId", required = false) String bedTypeId,
                            @RequestParam(value = "breakfast", required = false) String breakfast,
                            @RequestParam(value = "broadband", required = false) String broadband,
                            @RequestParam(value = "policy", required = false) String policy,
                            HttpSession session) {
        House house = new House();
        String hotelIdStr = RedisUtil.getJedis().get("hotelId");
        house.setHotelId(Integer.parseInt(hotelIdStr));
        if (bedTypeId != null && bedTypeId != "") {
            house.setBedTypeId(Integer.parseInt(bedTypeId));

        }

        house.setPolicy(policy);
        house.setBreakfast(breakfast);
        List<House> houses = houseService.findHouse(house);
        return JsonUtil.getJson(houses);
    }

    @RequestMapping(value = "/getHotelName")
    @ResponseBody
    public String getHotelName(HttpSession session) {
        String hotelName = (String) session.getAttribute("hotelName");
        Map<String, Object> map = new HashMap<>();
        map.put("hotelName", hotelName);
        return JsonUtil.getJson(map);
    }

    @RequestMapping(value = "/getTime")
    @ResponseBody
    public String getTime(HttpServletRequest request) {
        String hotelName = RedisUtil.getJedis().get("hotelName");
        String houseName = RedisUtil.getJedis().get("houseName");
        String hotelId = RedisUtil.getJedis().get("hotelId");
        String checkIn = RedisUtil.getJedis().get("checkIn");
        String checkOut = RedisUtil.getJedis().get("checkOut");
        Map<String, Object> map = new HashMap<>();
        map.put("hotelName", hotelName);
        map.put("houseName", houseName);
        map.put("hotelId", hotelId);
        map.put("checkIn", checkIn);
        map.put("checkOut", checkOut);
        return JsonUtil.getJson(map);
    }


    @RequestMapping(value = "/housePage/{checkOut}/{checkIn}/{hotelId}")
    public String housePage(@PathVariable(value = "checkOut") String beforeCheckOut,
                            @PathVariable(value = "checkIn") String beforeCheckIn,
                            @PathVariable(value = "hotelId") String beforeHotelId,
                            Model model) {
//        base64解码
        byte[] checkInByte = Base64.getDecoder().decode(beforeCheckIn);
        byte[] checkOutByte = Base64.getDecoder().decode(beforeCheckOut);
        byte[] hotelIdByte = Base64.getDecoder().decode(beforeHotelId);
        String checkIn = null;
        String checkOut = null;
        String hotelId = null;
        try {
          String  checkInBase64 = new String(checkInByte, "utf-8");
            String checkOutBase64 = new String(checkOutByte, "utf-8");
            String  hotelIdBase64 = new String(hotelIdByte, "utf-8");

//            urlEncoder解密
            checkIn = URLDecoder.decode(checkInBase64, "utf-8");
            checkOut = URLDecoder.decode(checkOutBase64, "utf-8");
            hotelId = URLDecoder.decode(hotelIdBase64, "utf-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String hotelName = hotelService.findHotelNameByid(Integer.parseInt(hotelId));
        String houseName = houseService.findHouseName(Integer.parseInt(hotelId));
        RedisUtil.getJedis().setex("houseName", 7200, houseName);
        RedisUtil.getJedis().setex("hotelName", 7200, hotelName);
        RedisUtil.getJedis().setex("hotelId", 7200, hotelId);
        RedisUtil.getJedis().setex("checkIn", 7200, checkIn);
        RedisUtil.getJedis().setex("checkOut", 7200, checkOut);
        model.addAttribute("checkIn", checkIn);
        model.addAttribute("checkOut", checkOut);
        return "housePage";
    }

    @RequestMapping(value = "/findBedType")
    @ResponseBody
    public String getBedType() {
        return JsonUtil.getJson(bedTypeService.findBed());
    }


    @RequestMapping(value = "/bookPage/{houseId}/{checkIn}/{checkOut}")
    public String bookPage(@PathVariable(value = "houseId") String beforeHouseId,
                           @PathVariable(value = "checkIn") String beforeCheckIn,
                                   @PathVariable(value = "checkOut") String beforeCheckOut,
                           Model model) {

//        base64解码
        byte[] houseIdByte = Base64.getDecoder().decode(beforeHouseId);
        byte[] checkInByte = Base64.getDecoder().decode(beforeCheckIn);
        byte[] checkOutByte = Base64.getDecoder().decode(beforeCheckOut);
        String checkIn = null;
        String checkOut = null;
        String houseId = null;
        try {
            String  checkInBase64 = new String(checkInByte, "utf-8");
            String checkOutBase64 = new String(checkOutByte, "utf-8");
            String  houseIdBase64 = new String(houseIdByte, "utf-8");

//            urlEncoder解密
            checkIn = URLDecoder.decode(checkInBase64, "utf-8");
            checkOut = URLDecoder.decode(checkOutBase64, "utf-8");
            houseId = URLDecoder.decode(houseIdBase64, "utf-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        jedis.setex("houseId", 901, houseId);
        String houseName = houseService.findHouseName(Integer.parseInt(houseId));
        model.addAttribute("houseName", houseName);
        model.addAttribute("houseId", houseId);
        model.addAttribute("checkIn", checkIn);
        model.addAttribute("checkOut", checkOut);
        return "bookPage";
    }

    @RequestMapping(value = "/housePrice")
    @ResponseBody
    public String housePrice() {
        Map<String, Object> map = new HashMap<>();
        map.put("price", houseService.findHousePrice(Integer.parseInt(jedis.get("houseId"))));
        return JsonUtil.getJson(map);
    }


    @RequestMapping(value = "/saveHouseHoldInfo")
    @ResponseBody
    public String saveHouseHoldInfo(@RequestParam(value = "trueName") String trueName,
                                    @RequestParam(value = "idNum") String idNum,
                                    @RequestParam(value = "phoneNum") String phoneNum, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        String userStr = RedisUtil.getJedis().get("user");
        User user = null;
        try {
            user = JsonUtil.getObjectMapper().readValue(userStr, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (user == null) {
            map.put("msg", "未登录");
            return JsonUtil.getJson(map);
        }
        List<CheckInUser> list = new ArrayList<>();
        CheckInUser checkInUser = new CheckInUser();
        checkInUser.setTrueName(trueName);
        checkInUser.setIdNum(idNum);
        checkInUser.setPhoneNum(phoneNum);

        checkInUser.setUserId(user.getId());
        list.add(checkInUser);
        session.getServletContext().setAttribute("checkInUser", list);
        map.put("msg", "已登录");
        return JsonUtil.getJson(map);
    }

    @RequestMapping(value = "/getHotel")
    @ResponseBody
    public String getHotel() {
        return JsonUtil.getJson(hotelService.findHotel());
    }


    @RequestMapping(value = "/getHouseCount")
    @ResponseBody
    public String getHouseCount(@RequestParam(value = "checkIn") String checkIn,
                                @RequestParam(value = "houseId") String houseId) {
        //查询订单，该酒店的该房间类型的订单数量
        Integer orderCount = orderService.findHouseCount(checkIn, Integer.parseInt(houseId));
        int houseCount = houseService.findHouseCount(Integer.parseInt(houseId));
        Map<String, Object> map = new HashMap<>();
        if (orderCount == null) {
            map.put("houseCount", houseCount);
        } else {
            map.put("houseCount", houseCount - orderCount);
        }
        return JsonUtil.getJson(map);
    }


    @RequestMapping(value = "/hotelMap")
    @ResponseBody
    public String hotelMap(@RequestParam(value = "id") String hotelId) {
        String hotelName = hotelService.findHotelNameByid(Integer.parseInt(hotelId));
        Map<String, Object> map = new HashMap<>();
        map.put("hotelName", hotelName);
        return JsonUtil.getJson(map);
    }

}
