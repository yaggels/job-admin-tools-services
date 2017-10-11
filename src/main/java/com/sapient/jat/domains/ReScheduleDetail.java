package com.sapient.jat.domains;
/**
 * 
 * Rescheduling details POJO.
 * 
 * @author Johnson Chow
 *
 */
public class ReScheduleDetail {
	
	private String triggerKey;
	
	private int startHour;
	private int startMin;
	private int startSec;
	
	private int endHour;
	private int endMin;
	private int endSec;
	/**
	 * Retrieves the instance id of the trigger being re-scheduled.
	 * 
	 * @return the trigger key.
	 */
	public String getTriggerKey() {
		return triggerKey;
	}
	/**
	 * Registers the instance id of the trigger being re-scheduled.
	 * 
	 * @param the trigger key
	 */
	public void setTriggerKey(String triggerKey) {
		this.triggerKey = triggerKey;
	}
	/**
	 * Retrieves the new starting hour.
	 * 
	 * @return <code>int</code> value of the new start hour.
	 */
	public int getStartHour() {
		return startHour;
	}
	/**
	 * Registers the new starting hour.
	 * @param value of the new start hour
	 */
	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}
	
	/**
	 * Retrieves the new starting minute
	 * 
	 * @return <code>int</code> value of the new starting minutes.
	 */
	public int getStartMin() {
		return startMin;
	}
	/**
	 * Registers the new starting minute 
	 * 
	 * @param value of the new starting minute
	 */
	public void setStartMin(int startMin) {
		this.startMin = startMin;
	}
	/**
	 * Retrieves the new starting second.
	 * 
	 * @return <code>int</code> value of the new starting seconds.
	 */
	public int getStartSec() {
		return startSec;
	}
	/**
	 * Registers the new starting second.
	 * 
	 * @param value of the new starting seconds
	 */
	public void setStartSec(int startSec) {
		this.startSec = startSec;
	}
	/**
	 * Retrieves the new end hour.
	 * 
	 * @return <code>int</code> value of the new end hour.
	 */
	public int getEndHour() {
		return endHour;
	}
	/**
	 * Registers the new end hour
	 * 
	 * @param value of the new end hour
	 */
	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}
	/**
	 * Retrieves the new end minute
	 * 
	 * @return <code>int</code> value of the new end minute.
	 */
	public int getEndMin() {
		return endMin;
	}
	/**
	 * Registers the new end minute
	 * 
	 * @param value of the new end minute
	 */
	public void setEndMin(int endMin) {
		this.endMin = endMin;
	}
	/**
	 * Retrieves the new end seconds.
	 * 
	 * @return <code>int</code> value of the new end seconds.
	 */
	public int getEndSec() {
		return endSec;
	}
	/**
	 * Registers the new end seconds.
	 * 
	 * @param value of the new end second.
	 */
	public void setEndSec(int endSec) {
		this.endSec = endSec;
	}
}
