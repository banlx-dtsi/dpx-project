package com.axelor.apps.purchase.db;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Track;
import com.axelor.db.annotations.TrackEvent;
import com.axelor.db.annotations.TrackField;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.db.hibernate.type.JsonType;
import com.axelor.message.db.Message;
import com.axelor.message.db.Template;
import com.axelor.meta.db.MetaFile;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import org.hibernate.annotations.Type;

@Entity
@Table(
  name = "PURCHASE_CALL_TENDER_MAIL",
  indexes = {
    @Index(
      columnList = "mail_template"
    ),
    @Index(
      columnList = "sent_message"
    ),
    @Index(
      columnList = "meta_file"
    )
  }
)
@Track(
  on = TrackEvent.UPDATE,
  fields = {
    @TrackField(
      name = "mailTemplate"
    ),
    @TrackField(
      name = "sentMessage"
    ),
    @TrackField(
      name = "metaFile"
    )
  }
)
public class CallTenderMail extends AuditableModel {

  @Id
  @EntitySequence(
    name = "PURCHASE_CALL_TENDER_MAIL_SEQ"
  )
  private Long id;

  @Widget(
    title = "Mail template"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Template mailTemplate;

  @Widget(
    title = "Sent message"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Message sentMessage;

  @Widget(
    title = "Attachment",
    copyable = false
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MetaFile metaFile;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public CallTenderMail() {
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public Template getMailTemplate() {
    return mailTemplate;
  }

  public void setMailTemplate(Template mailTemplate) {
    this.mailTemplate = mailTemplate;
  }

  public Message getSentMessage() {
    return sentMessage;
  }

  public void setSentMessage(Message sentMessage) {
    this.sentMessage = sentMessage;
  }

  public MetaFile getMetaFile() {
    return metaFile;
  }

  public void setMetaFile(MetaFile metaFile) {
    this.metaFile = metaFile;
  }

  public String getAttrs() {
    return attrs;
  }

  public void setAttrs(String attrs) {
    this.attrs = attrs;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (this == obj) return true;
    if (!(obj instanceof CallTenderMail)) return false;

    final CallTenderMail other = (CallTenderMail) obj;
    if (this.getId() != null || other.getId() != null) {
      return Objects.equals(this.getId(), other.getId());
    }

    return false;
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
     .add("id", getId())
      .omitNullValues()
      .toString();
  }
}
