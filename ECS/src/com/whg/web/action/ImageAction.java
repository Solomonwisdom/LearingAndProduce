package com.whg.web.action;

import com.whg.web.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Map;

public class ImageAction extends BaseAction {

	private InputStream imageStream;

	public String execute() {
		Map<String,BufferedImage> map=ImageUtil.createImage();
		String code=map.keySet().iterator().next();
		session.put("code", code);
		System.out.println(code);
		BufferedImage image=map.get(code);
		try {
			imageStream=ImageUtil.change(image);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}public InputStream getImageStream() {
		return imageStream;
	}
	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}

}
