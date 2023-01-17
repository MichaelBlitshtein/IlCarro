package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class Car {
    private String location;
    private String manufacture;
    private String model;
    private String year;
    private String fuel;
    private String seats;
    private String claSS;
    private String carRegNumber;
    private String price;
    private String about;

}
