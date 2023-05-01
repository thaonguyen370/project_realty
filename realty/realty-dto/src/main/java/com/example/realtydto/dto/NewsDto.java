package com.example.realtydto.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto implements Serializable {

    private Integer id;

    private String title;

    private String content;

    private Integer status;

    private String author;

    private String location;

    private String price;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date updatedDate;


    private String note1;


    private String note2;

    private List<ImageDto> images=new ArrayList<>();

    private Boolean isDashboard;
}
