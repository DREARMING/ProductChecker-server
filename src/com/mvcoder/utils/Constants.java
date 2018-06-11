package com.mvcoder.utils;

public class Constants {
	
	public static final String ACCESS_KEY = "IWaSmKzbQcCS2ZyW9E3Cap4Cpi6PPW0b391Imihe";
	public static final String SECRET_KEY = "MPSIbQzH6moltxuc0Jy4pPRwSiu2yLgXXdaEexxW";
	
	public static final String PUBLISH_URL = "publish-rtmp.xt-hacker.com";
	public static final String LIVE_URL = "live-rtmp.xt-hacker.com";
	public static final String SNAPSHOT_URL = "snapshot.xt-hacker.com";
	public static final String DOWNLOAD_URL = "http://ooe2su3zf.bkt.clouddn.com/";
	
	public static final String HUB_NAME = "mliveroom";
	
	public static final String PREFIX = "MLive";
	
	public static final int PUBLISH_TIME_OUT =  2 * 3600 ; //2Сʱ����
	
	public  static final String APPLICATION_CONTEXT = "applicationContext.xml";
	
	//һ��Ϊ����Token
	public static final long TOKEN_TTL = 7 * 24 * 60 * 60 * 1000 ;
	
	//COOKIE��״̬��������Ϣ��
	public static final String TOKEN_NAME = "Authority";

	public static final String PROJECT_ROOT_DIR = "/pdchecker";
	public static final String PROJECT_UPLOAD_DIR = "/upload";

	
	public static class LOGINSTATE{
		public static final int LOCAL = 0;
		public static final int WEIXIN = 1;
		public static final int QQ = 2;
	}
	
	public static class User{
		public static final String headImgUrl = "";
	}
	
	public static class Security{
		public static final String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCSqK3yjIGl6g5oVTwOuQdzqTLO2PO/H+rylf8DDbAVNmEb+hvIwEvEXE3JhxCImiLDTKEEOF3GcmfPEgdUP+XszMhji09IAXXJQxaKggkafOhEU/QVyqqD5V8IIjVmMXKxfgcJPbhCWstc6LUnaoJ3UgvDhgoBC65Tj6Apfy4VTQvf9x1X1LImSzPZUoQJpCLPRQmHaifo+X2GDU+1xfpV+QcvyAgHcnERs9aKgaNzqLa5BzskMVGPvYISTJ+MrwfhcJbxNWbL+BhqCqdq+0IjbJwkbDMpC0qg7ztzelUhUDU/oKQhJBmCRm32giT4vxwu9trfPQpwXTkKZdFrtdQ/AgMBAAECggEAGQAwRvy0zTfNJPQCvO/F2W2qf6B0TMyAHMJ9KmQW9EbE3yQPwHUndV3W1Nf1dZPxASH7AOEq+z1dfOsx3KbX5zoxnuaMqVW2YozRNuNb8ewBHETDg0N9NebQAG70c2tShfdZ87VVx9n6eOgimYmKnBScdMRQyETZft0pDGnlzTOGjLF3hdh0koR4l4u/XAqW5vjFR60ByMsR6uu2WbBgnHvWXJHJT5Jt0CKIYFxGeSYmA3xFplld8cfArvmsbzA8kWiZ7/yJSm2VZkTCUVdbmC8LBgXoEoZATH3YmupzCO9ZZvm6GNluF6X1Xkebd07dHFD0SQQomlEykUSP7AvZwQKBgQDcJjYGBEZcxmy/ktnR46uVnVb1S8w8ay0vcdUWNn5ij4VfataUwLo2jpVvzzpKxKUAqewi4GTX6RJDNTUysoVE+84mnPylOAkIPkwkAvUp6iNIgDk5reRo5IrzYEx/Ke2laM+UiMOaSfl2f/UgfGMQ/SR7uV+5sCAAvQbr79QgqQKBgQCqirvFIAt7hxMWDP6KjplvXtmKvG9qfNymTLwuvbpLrfgJGbkhHqvSsmf9oCIRhk3hppBMipS9lsX0AiirnjQfzNwyuLaqIyEFRFnEnlaj667+SJybnRqI8smJxqqhAJZ6bfrshd93lm6U7PNEfNsp07vPYipFVCIpH0lr1xYWpwKBgQCwt/ul2MiisKWbIsa3NYsHDsY/IhlQZrhe3YGv0w6hD5SAg8K8pfjU0qAioLaqTjAl2EE1ves1ZmLCkHOjvqo8NeXGGT61YcXeWCPGxJkIjGrGSCenpW80xEDGHQdLDN03ams3UJGzyK338b9T1IWxnYMrziKJbFhEP3jO7DXcAQKBgHZgCenQTmgV4AekSWJ/jL0jRzjnhyaROM8t4ElJZHB3dv1KH5h/o6CCOXBwoHUtlg2VAgA/CPN4Hjs67GQDUgboucdzM0dxTtBLG0xA476wLy1N1XamW+7HJ2E8xt8ue0TL5ioa2nw1rNguiMT3LWEhGPHgH5szxOpqTxpNl4VtAoGAcGQ9f8qMcElQt91SWp6Sks0JTf15RU8Cvrx2j6WqP/3Epe/2g236+tjMJT+NP2oPw9OG/HoQmOR9hnlqAsm+ygiXGRedVcQyz47y5x8+gqDjzz6bXHeRjk9hHkwj4Tm7Vpa4qb3nezV2BFpA1D6md7R5Ve0iTY1LFTivPgWIGlQ=";
		
		public final static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkqit8oyBpeoOaFU8DrkHc6kyztjzvx/q8pX/Aw2wFTZhG/obyMBLxFxNyYcQiJoiw0yhBDhdxnJnzxIHVD/l7MzIY4tPSAF1yUMWioIJGnzoRFP0Fcqqg+VfCCI1ZjFysX4HCT24QlrLXOi1J2qCd1ILw4YKAQuuU4+gKX8uFU0L3/cdV9SyJksz2VKECaQiz0UJh2on6Pl9hg1PtcX6VfkHL8gIB3JxEbPWioGjc6i2uQc7JDFRj72CEkyfjK8H4XCW8TVmy/gYagqnavtCI2ycJGwzKQtKoO87c3pVIVA1P6CkISQZgkZt9oIk+L8cLvba3z0KcF05CmXRa7XUPwIDAQAB";
	}
	
	public static class StateCode{
		public static final int OK = 200;
		
		public static final int FAIL = -1;
		
		public static final int COOKIE_VAILD = 111;
		public static final int COOKIE_TIME_OUT = 110;
		public static final int COOKIE_INVAILD = 112;
		public static final int COOKIE_NONE = 113;
	}
	
	public static class ThridLogin{
		public static final String QQ = "qq";
	}
	public static class LiveState{
		public static final byte INIT = 0;
		public static final byte LIVING = 1;
		public static final byte STOP = 2;
		public static final byte END = 3;
	}
}
