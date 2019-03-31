package com.milekj.bookingdotmock.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class RoomSearchDTO {
    private static final String dateFormat = "yyyy-MM-dd";
    @NotBlank(message = "City cannot be empty")
    private String city;

    @NotNull(message = "Cannot be empty")
    @Future(message = "Start date must be future")
    @DateTimeFormat(pattern = dateFormat)
    private LocalDate startDate;

    @NotNull(message = "Cannot be empty")
    @Future(message = "End date must be future")
    @DateTimeFormat(pattern = dateFormat)
    private LocalDate endDate;

    @Min(value = 1, message = "Guests number must be greater than 0")
    @NotNull(message = "Cannot be empty")
    private Integer guestsNumber;

    @DecimalMin(value = "0.0", message = "Price must be greater than 0")
    @NotNull(message = "Cannot be empty")
    private BigDecimal minPrice;

    @DecimalMin(value = "0.0", message = "Price must be greater than 0")
    @NotNull(message = "Cannot be empty")
    private BigDecimal maxPrice;

    @AssertTrue(message = "Start date must be before end date")
    private boolean datesValid;

    @AssertTrue(message = "Minimal price must be lower than maximal price")
    private boolean pricesValid;

    public RoomSearchDTO() {
        datesValid = false;
        pricesValid = false;
    }

    private void refreshDatesValid() {
        if (startDate != null && endDate != null)
            datesValid = startDate.isBefore(endDate) || startDate.equals(endDate);
    }

    private void refreshPricesValid() {
        if (minPrice != null && maxPrice != null)
            pricesValid = (minPrice.compareTo(maxPrice) <= 0);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        refreshDatesValid();
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        refreshDatesValid();
    }

    public Integer getGuestsNumber() {
        return guestsNumber;
    }

    public void setGuestsNumber(Integer guestsNumber) {
        this.guestsNumber = guestsNumber;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
        refreshPricesValid();
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
        refreshPricesValid();
    }

    public boolean isDatesValid() {
        return datesValid;
    }

    public void setDatesValid(boolean datesValid) {
        this.datesValid = datesValid;
    }

    public boolean isPricesValid() {
        return pricesValid;
    }

    public void setPricesValid(boolean pricesValid) {
        this.pricesValid = pricesValid;
    }
}
