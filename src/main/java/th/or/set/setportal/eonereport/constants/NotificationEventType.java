package th.or.set.setportal.eonereport.constants;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum NotificationEventType {
    SENT_TO_APPROVE,
    CANCEL_SENT_TO_APPROVE,
    APPROVE,
    REJECT,
    SEND_TO_PUBLISH,
    PUBLISH,
    SENT_TO_CANCEL_PUBLISH,
    APPROVE_CANCEL_PUBLISH,
    REJECT_CANCEL_PUBLISH
}
