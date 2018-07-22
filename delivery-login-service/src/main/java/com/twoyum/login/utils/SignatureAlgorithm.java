package com.twoyum.login.utils;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.codec.binary.Base64.*;

public class SignatureAlgorithm {

	// 默认签名算法
	public static final String ALGORITHM = "MD5withRSA";

	private static final PrivateKey PRI_KEY;

	private static final PublicKey PUB_KEY;

	static {
		try {
			PRI_KEY = KeyFactory.getInstance("RSA")
					.generatePrivate(new PKCS8EncodedKeySpec(decodeBase64(
							"MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMCLIAP8X6k9SZ90-91QGnTL2SpkvIJGlX4DHmG-7IGy4X5l0r5VwOZSPIcXMITBkKtPMbiBn2JEjKJemDgwzoTwLuxbjpeeZG_Asv540wid6TTz4WM-vnkvEC5p8vdhzOGpwXm_vxmSkJ_zYFWaPa-F7g5dIqWiBF5giQeGkIPlAgMBAAECgYAM13qcKXHr5LWLWA8QVK5fCdWDGVLlUXXZALY_CK_XeqnFd18V6LxD4Eo_LFHFGO_gLRkA2ExcDnxeu_ZlEQRFBekDtRqSNeAlEpRc5kZK5K-ADakvj-mB1SFDvQnvszQ7axdTfmLzWeXOcUETjtkTlj7hSZCFVN17jO3Om11yxQJBAOaO1ar2rtBq9ptna8jQuIvnJkeWmDmmDbx3DZCXQ9NLUDCQBMXYAgYp6VEJVnLINPzYnTirHU2cWujnSNwE3qcCQQDVymWh4GXhGvog0YxTKvLy4q4kz42ilIo3jlwLGfHsVfUUD78F0xPAG34N1wl3bOb792yMUaUuWRzrkz9FZUaTAkBfDzM_mxf2rLNi3aBOGuiMHlDlIU1AL5voQbnhDM0VOC8m5qphHC-xdORS2iO_jZZzTah9LegWEMpWzs12kbdvAkBLB3UTkoUusyEFU4VDytboltU25gB3BlpblQKIgtp8bs5L9Bq-GI3d5cgfY1BXMUj_NJ5LwHuQAIY9xrd1y00xAkBgHls0zUUDkyDJuznKoR0kvxedDceK7OLSUELa4-sJ47tYtLbVnUK_SOcyGqe2C6WrF-2Tev6DCzmeLiYD2pdK")));
			PUB_KEY = KeyFactory.getInstance("RSA")
					.generatePublic(new X509EncodedKeySpec(decodeBase64(
							"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAiyAD/F+pPUmfdPvdUBp0y9kqZLyCRpV+Ax5hvuyBsuF+ZdK+VcDmUjyHFzCEwZCrTzG4gZ9iRIyiXpg4MM6E8C7sW46XnmRvwLL+eNMInek08+FjPr55LxAuafL3YczhqcF5v78ZkpCf82BVmj2vhe4OXSKlogReYIkHhpCD5QIDAQAB")));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean verify(String sign, String content) {
		return verify(PUB_KEY, ALGORITHM, sign, content);
	}

	public static boolean verify(PublicKey key, String sign, String content) {
		return verify(key, ALGORITHM, sign, content);
	}

	public static boolean verify(PublicKey key, String algorithm, String sign,
			String content) {
		try {
			return verify(sign, algorithm, content, key);
		} catch (Exception e) {
			return false;
		}
	}

	private static boolean verify(String sign, String algorithm, String content,
			PublicKey key) throws Exception {
		Signature s = Signature.getInstance(algorithm);
		s.initVerify(key);
		s.update(content.getBytes(UTF_8));
		return s.verify(decodeBase64(sign));
	}

	public static String getSignValue(String content) {
		return getSignValue(PRI_KEY, ALGORITHM, content, 1);
	}

	public static String getSignValue(PrivateKey key, String content) {
		return getSignValue(key, ALGORITHM, content, 1);
	}

	public static String getSignValue(PrivateKey key, String content,
			int result) {
		return getSignValue(key, ALGORITHM, content, result);
	}

	public static String getSignValue(PrivateKey key, String algorithm,
			String content, int result) {
		try {
			return getSignValue(content, algorithm, key, result);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String getSignValue(String content, String algorithm,
			PrivateKey key, int result) throws Exception {
		Signature s = Signature.getInstance(algorithm);
		s.initSign(key);
		s.update(content.getBytes(UTF_8));
		return result == 1 ? encodeBase64URLSafeString(s.sign())
				: encodeBase64String(s.sign());
	}

}
