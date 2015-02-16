package se.rapid.web.controller;

import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;

import se.rapid.domain.UploadItem;
import se.rapid.initializer.MongoConfig;

@Controller
@RequestMapping
public class UploadController
{

	@Autowired
	GridFsTemplate gridFsTemplate;


	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String getUploadForm(Model model)
	{
		model.addAttribute(new UploadItem());
		return "/upload";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String create(@ModelAttribute("uploadItem") UploadItem uploadItem, BindingResult result) throws Exception
	{
		
//		MongoClient client = new MongoClient();
//		DB db = client.getDB("booking");
//		FileInputStream inputStream = null;
//
//		GridFS videos = new GridFS(db, "image"); // returns GridFS bucket named
													// "videos"
		System.out.println("uploadItem: "+uploadItem);
		if (result.hasErrors())
		{
			for (ObjectError error : result.getAllErrors())
			{
				System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
			}
			return "/upload";
		}

		System.out.println("not uploaded" + uploadItem.getFileData().getInputStream());
		System.out.println("not uploaded" + uploadItem.getFileData().getOriginalFilename());

		gridFsTemplate.store(uploadItem.getFileData().getInputStream(), uploadItem.getFileData().getOriginalFilename());
		System.out.println("uploaded");
		return "/uploadSuccess";
	}
}