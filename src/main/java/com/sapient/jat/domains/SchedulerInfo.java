package com.sapient.jat.domains;

import org.springframework.hateoas.Identifiable;

public class SchedulerInfo implements Identifiable<String> {
	
	private final String name;
	private final boolean isInStandbyMode;
	private final boolean isStarted;
	private final boolean isShutdown;
	
	public SchedulerInfo(String name, boolean isInStandbyMode, boolean isStarted,
			boolean isShutdown) {
		
		this.name = name;
		this.isInStandbyMode = isInStandbyMode;
		this.isStarted = isStarted;
		this.isShutdown = isShutdown;
	}
	
	@Override
	public String getId() {
		return name;
	}

	public boolean isInStandbyMode() {
		return isInStandbyMode;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public boolean isShutdown() {
		return isShutdown;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isInStandbyMode ? 1231 : 1237);
		result = prime * result + (isShutdown ? 1231 : 1237);
		result = prime * result + (isStarted ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchedulerInfo other = (SchedulerInfo) obj;
		if (isInStandbyMode != other.isInStandbyMode)
			return false;
		if (isShutdown != other.isShutdown)
			return false;
		if (isStarted != other.isStarted)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SchedulerInfo [name=" + name + ", isInStandbyMode=" + isInStandbyMode + ", isStarted=" + isStarted
				+ ", isShutdown=" + isShutdown + "]";
	}

}
