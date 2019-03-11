package com.wu.util;

public class AlipayConfig {
    // 商户appid
    public static String APPID = "2016092800612844";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDSzxeTWZq5ZAxsvZmFpn6SJC5mIJh06cqx8n10kIGpx3z+gnqZlILEYMaOxOQ3WsWhx+uC01lViAjALTZ1Sm21iSfzhV5RqjFaHYvRuj1gCGsm69QQ6bz17rwZ5H2v82Z7N2pHrpwbtl4WaScTjsRTy8SVzBpICsJZzqGVnsp1UbgjvwUC95DRv5hxKWnxCwg2Zfa59km0N2hzrHRs86GKf+vet7vXlafZNdtrRWUD83vsetdOViR9ECqQ/q4m017ZH1RrIco5isEu71cgsgX71gMIzmsj4n+V21qJkamUxZ2Ni1qEeBg8B/bFXVohsuha+BYbcJOgY/cu1nSDsLCjAgMBAAECggEAQ1MEEnjxYvfCyA+dMgPENGP9tXv/dIcdxsrMqidkYZZmItoE0XuOb60AfKQAxnTk3Q/99OAOjbROJyHJXJgShYFyEuGrB6W08TSybJ5v73WA0wNJzMT5JgnCZPqF8WVoez1tYcsv6+HIFpwqcKKNrL1M4RI36n4VJ51IGYKG4gRMwl+CTGb8sSiKqOjD1wZkMfyMODlYcbNo3bKa46JPs3iQ53BHayu5xyTHuqXRS64jjyQ5uNAms3MLHTDPqi54y89XmGIUfqOhLe0kEY2PlWErv976EkZHOJMBLckECZTTXTDq9MsJqaGOaak5SopN9Jknengb6E/BbM2uALM7oQKBgQDtLKV8WH6QwE8EnOx0yHnrL1oZvw8nK+b6K7x611vs4m1jCbWNIx++x+39vuO9SzQwYozfacFgOomsLz178hBo2PKPAJa7DxcNthglJCuBjnls7GCovY23GRFIkgij2kEwcLQgpVxYifvWVS2oP1saVMu9FAeDR26ftqcs21rAyQKBgQDjirQBnAxsHf+WYT85NzeQ1A6yxdlatcRszLnuG8d28LkFirOS7zre7ezz3isOOcT0iNTVtMIsywE3EiFSwOfQX7lEO61SCZjpJe7sh2dQT4IZGE9m8ZFCt1NIiHkfuZPArSQ7cM6c7RW9bHKvnK3CikgWWUqae2falOPga9MoCwKBgEORt6fAUnaMwzZOOrNethVBzUqOexOu10Qol67rkf3f2Egh6P5HpnCk5vGmo503QqwSN44iw/vVUYmjf8HDCdfFDv2crlSmjOvUF/bk4hwr6f4VLciFEE28r4bcS+facz4IrdirI675yKycYcCQJDVROSYqAaXuAN8xm1kVOTq5AoGAMG1Iuj739dq8lsjDp+20icJYDnxFMiUVVQKNX7vRy+KXvjIy3cbEhMWmdJCIIVLyNAkbIxCBcv8RlyLorLAZpiN5TqhUzuzz+kr0eqqmtlT1n1mpLIOlIsnZk8pX3QSmCY/P8kaj8ooS4mm+TtnNsWW0yezfD9nL1J1zkkPggNkCgYACUPtllUpd1Hw3HL6XfvN+ex7SOvS5KIyRqjHF17WooaS17F68IbKHBQdp5+q0ULnXmmNxybHEh4XZ7Zh2m2zOKpdTV87u4dqOs8jGyKlA+KtVfCau8UcVWL/Et+Z/1Vqgk9GKLfjtGH17XSz5Pmh9uV+yv8McAK9zkx/tGnwTyA==";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://192.168.31.158/notify_url";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://192.168.31.158/return_url";
    // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA9KA2EVi9JyI1yFr/Kev0GpCFS2nfBT8qG2lObvKgY1ILYnDHASM8XxwZUmCTOJMLu8R3EDjm7DmsqHgUgg4HS6XbheDRTLtVnGrufPWszqr4NRhjR4Jvx0A6VTj9UNLj7/tbN9Kt/tpejEoe/cRGJxIPGqcts9cI1Jp3ebO39ZzS2R2bwdUYyT4FuGObueFn0svMQ3bj1CHjFqzZMBCEzi+L/TO+mrvQPp66UcwfiSowcdAxBoTDbWZDVaW5tK34bgcDcKQxunn3VM+NgctsZRwfGqZQweua/aHwr2rEL8EsjqJSzFmMS07mgN+X9T9MnVKzRITQFFj7c8MNIKZ9/wIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}
