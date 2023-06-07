package com.smhrd.shop.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.smhrd.shop.converter.ImageConverter;
import com.smhrd.shop.converter.ImageToBase64;
import com.smhrd.shop.domain.Product;
import com.smhrd.shop.mapper.ProductMapper;

@Service
public class ProductService {
	@Autowired
	private ProductMapper mapper;
	
	@Autowired
	private ResourceLoader resourceLoader;
	//특정 경로에 있는 파일을 가지고 오기
	
	//product 전체 정보 불러올 때 사용
	public JSONArray productList() {
		List<Product> list = mapper.productList();
		
		//list(Product-> img (파일이름만 가지고 있음, 실제 파일x)
		//Product -> img(파일이름-dress1.jpeg) -> 실제 파일 가지고 오기(static/img/...)
		//파일을 응답해줄 떄 (파일의 형태가 중요 : byte형태로 변환 해야함!)
		//Product 의 img 필드 값을 이미지를 바이트 문자열 형태로 바꾼걸로 수정!
		
		//JsonArray 에 JsonObject가 들어있는 형식으로 응답
		//List -> JsonArray
		JSONArray jsonArray = new JSONArray();
		ImageConverter<File, String> converter= new ImageToBase64();
		//Product -> JsonObject
		for(Product p: list) {
			//1. img 필드값 수정 ( 파일이름 -> 이미지 바이트 문자열 형태)
			//-1. 변환할 파일 실제 경로 정의
			String filePath = "classpath:/static/img/"+p.getImg();
			Resource resource = resourceLoader.getResource(filePath);
			String fileStringValue = null;
			try {
				fileStringValue = converter.convert(resource.getFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println(fileStringValue);
			p.setImg(fileStringValue);
			//2. Product -> JsonObject(key:value) 변환
			JSONObject obj = new JSONObject(); //비어있는 json object 생성
			obj.put("product", p); //비어있는 object에 값을 추가한 것
			
			jsonArray.add(obj);
		}
		return jsonArray;
	}
	
	public JSONObject productOne(String pcode) {
		Product product = mapper.productOne(pcode);
		JSONObject obj = new JSONObject();
		ImageConverter<File, String> converter= new ImageToBase64();
		String filePath = "classpath:/static/img/"+product.getImg();
		Resource resource = resourceLoader.getResource(filePath);
		String fileStringValue = null;
		try {
			fileStringValue = converter.convert(resource.getFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(fileStringValue);
		product.setImg(fileStringValue);
		//2. Product -> JsonObject(key:value) 변환
		obj.put("product", product); //비어있는 object에 값을 추가한 것
		
		

		return obj;
	}
	
}
