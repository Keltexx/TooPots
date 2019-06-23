package es.uji.ei102718cln.TooPots.model;

public class Payment {
		int paymentId;
		int reservationId;
		String bankAccount;
		String customerId;
		public int getPaymentId() {
			return paymentId;
		}
		public void setPaymentId(int paymentId) {
			this.paymentId = paymentId;
		}
		public int getReservationId() {
			return reservationId;
		}
		public void setReservationId(int reservationId) {
			this.reservationId = reservationId;
		}
		public String getBankAccount() {
			return bankAccount;
		}
		public void setBankAccount(String bankAccount) {
			this.bankAccount = bankAccount;
		}
		public String getCustomerId() {
			return customerId;
		}
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}
		@Override
		public String toString() {
			return "Payment [paymentId=" + paymentId + ", reservationId=" + reservationId + ", bankAccount="
					+ bankAccount + ", customerId=" + customerId + "]";
		}
		
		
}
