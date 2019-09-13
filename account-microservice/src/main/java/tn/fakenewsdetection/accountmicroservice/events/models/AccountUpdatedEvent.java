package tn.fakenewsdetection.accountmicroservice.events.models;

import java.io.Serializable;

public class AccountUpdatedEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private int accountId;
	private String username;

	public AccountUpdatedEvent() {
	}

	public AccountUpdatedEvent(int accountId, String username) {
		super();
		this.accountId = accountId;
		this.username = username;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		AccountUpdatedEvent other = (AccountUpdatedEvent) obj;
		if (accountId != other.accountId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AccountUpdatedEvent [accountId=" + accountId + ", username=" + username + "]";
	}

}
