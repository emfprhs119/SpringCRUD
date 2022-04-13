package com.min.mutabledata.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "MOCK_DATA")
public class MockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String app_name;
    private String url;
    private String company;
    private String email;

    @Builder
    public MockEntity(String app_name, String url, String company, String email){
        this.app_name = app_name;
        this.url = url;
        this.company = company;
        this.email = email;
    }
}
