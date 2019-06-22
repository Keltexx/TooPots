package es.uji.ei102718cln.TooPots.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import es.uji.ei102718cln.TooPots.model.Photos;

public final class PhotosRowMapper implements RowMapper<Photos> {
    public Photos mapRow(ResultSet rs, int rowNum) throws SQLException {
        Photos photos = new Photos();
        photos.setActivityId(rs.getInt("interestedid"));
        photos.setPhoto(rs.getString("photoid"));
        
        return photos;
    }}