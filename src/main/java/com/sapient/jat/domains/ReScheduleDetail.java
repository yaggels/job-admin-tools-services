package com.sapient.jat.domains;

public class ReScheduleDetail {
	
	private String triggerKey;
	
	private int startHour;
	private int startMin;
	private int startSec;
	
	private int endHour;
	private int endMin;
	private int endSec;
	
	public String getTriggerKey() {
		return triggerKey;
	}
	public void setTriggerKey(String triggerKey) {
		this.triggerKey = triggerKey;
	}
	public int getStartHour() {
		return startHour;
	}
	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}
	public int getStartMin() {
		return startMin;
	}
	public void setStartMin(int startMin) {
		this.startMin = startMin;
	}
	public int getStartSec() {
		return startSec;
	}
	public void setStartSec(int startSec) {
		this.startSec = startSec;
	}
	public int getEndHour() {
		return endHour;
	}
	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}
	public int getEndMin() {
		return endMin;
	}
	public void setEndMin(int endMin) {
		this.endMin = endMin;
	}
	public int getEndSec() {
		return endSec;
	}
	public void setEndSec(int endSec) {
		this.endSec = endSec;
	}
}
