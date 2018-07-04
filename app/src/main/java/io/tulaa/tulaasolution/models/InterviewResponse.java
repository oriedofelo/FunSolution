package io.tulaa.tulaasolution.models;

import java.util.List;

public class InterviewResponse {
	private int countTripletsSmallerSumResponse;
	private boolean tripletResponse;
	private List<Integer> pascalResponse;

	public int getCountTripletsSmallerSumResponse() {
		return countTripletsSmallerSumResponse;
	}

	public void setCountTripletsSmallerSumResponse(int countTripletsSmallerSumResponse) {
		this.countTripletsSmallerSumResponse = countTripletsSmallerSumResponse;
	}

	public boolean isTripletResponse() {
		return tripletResponse;
	}

	public void setTripletResponse(boolean tripletResponse) {
		this.tripletResponse = tripletResponse;
	}

	public List<Integer> getPascalResponse() {
		return pascalResponse;
	}

	public void setPascalResponse(List<Integer> pascalResponse) {
		this.pascalResponse = pascalResponse;
	}

}
