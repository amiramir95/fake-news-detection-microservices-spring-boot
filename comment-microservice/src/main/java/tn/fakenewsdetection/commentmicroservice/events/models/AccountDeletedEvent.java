package tn.fakenewsdetection.commentmicroservice.events.models;

import java.io.Serializable;

public class AccountDeletedEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private int accountId;

	public AccountDeletedEvent() {
	}

	public AccountDeletedEvent(int accountId) {
		super();
		this.accountId = accountId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "AccountDeletedEvent [accountId=" + accountId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
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
		AccountDeletedEvent other = (AccountDeletedEvent) obj;
		if (accountId != other.accountId)
			return false;
		return true;
	}

}
