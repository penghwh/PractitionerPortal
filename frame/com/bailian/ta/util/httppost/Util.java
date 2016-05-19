package com.bailian.ta.util.httppost;

public class Util {

	public String dkhzh = "";
	public String mac = "";

	public Util(String orderid, String dptflag, String userid, String payamount, String callbackurl, String payUserId,
			String overtime, String timestamp) {
		String dzffs = "02";
		String dddbh = orderid;
		String client_id = Common.client_id;
		String client_secret = Common.client_secret;
		String cardNum = userid;
		// SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHdd");
		// String timestamp = sf.format(new Date());

		String jdkmms = client_secret.substring(client_secret.length() - 4)
				+ timestamp.substring(timestamp.length() - 4);

		dkhzh = "";

		try {
			dkhzh = AESUtil.encrypt(jdkmms, cardNum, MD5Security.md5(jdkmms));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		mac = MD5Utils.string2MD5(dddbh + dptflag + cardNum + dzffs + payamount + timestamp + client_id
				+ client_secret.substring(client_secret.length() - 4), 32);

		mac = MD5Utils.string2MD5(
				dddbh + dptflag + cardNum + timestamp + payUserId + "01|02|03" + payamount + payamount + callbackurl
						+ callbackurl + overtime + client_id + client_secret.substring(client_secret.length() - 4),
				32);

	}

	public String getMac() {
		return mac;
	}

	public String getdkhzh() {
		return dkhzh;
	}

}
