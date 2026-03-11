package com.axelor.message.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.message.db.Message;

public class MessageRepository extends JpaRepository<Message> {

  public MessageRepository() {
    super(Message.class);
  }

  // typeSelect
  public static final int TYPE_RECEIVED = 1;
  public static final int TYPE_SENT = 2;

  // statusSelect
  public static final int STATUS_DRAFT = 1;
  public static final int STATUS_IN_PROGRESS = 2;
  public static final int STATUS_SENT = 3;
  public static final int STATUS_DELETED = 4;

  // mediaTypeSelect
  public static final int MEDIA_TYPE_MAIL = 1;
  public static final int MEDIA_TYPE_EMAIL = 2;
  public static final int MEDIA_TYPE_CHAT = 3;
  public static final int MEDIA_TYPE_EMAILING = 4;
  public static final int MEDIA_TYPE_SMS = 5;
}
