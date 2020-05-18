package com.landsoft.Rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.landsoft.Model.Album;
import com.landsoft.Repo.IAlbumRepo;

@RestController
public class AlbumResController {

	@Autowired
	private IAlbumRepo repo;

	@GetMapping(path = "/album/{albumid}")
	public Album findAlbum(@PathVariable("albumid") Integer albumId) throws IOException {
		Album album = null;
		try {
			album = repo.findById(albumId).get();
			return album;
		} catch (Exception e) {
			URL url = new URL("https://jsonplaceholder.typicode.com/albums/" + albumId);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
			StringBuilder sb = new StringBuilder();

			String line;
			
			while ((line = in.readLine()) != null) {
				sb.append(in);
				
			}
			JSONObject json = new JSONObject(sb.toString());
			album.setId(json.getInt("id"));
			album.setTitle(json.getString("title"));
			album.setUserId(json.getInt("userId"));
			repo.save(album);
			return album;
		}
	}
}
