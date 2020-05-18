package com.landsoft.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.landsoft.Model.Album;

public interface IAlbumRepo extends JpaRepository<Album, Integer> {

}
