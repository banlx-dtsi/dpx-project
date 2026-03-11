package com.axelor.message.db;

import com.axelor.apps.base.db.PrintTemplateLine;
import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.db.hibernate.type.JsonType;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import org.hibernate.Length;
import org.hibernate.annotations.Type;

@Entity
@Table(
  name = "MESSAGE_TEMPLATE_CONTEXT",
  indexes = {
    @Index(
      columnList = "name"
    ),
    @Index(
      columnList = "template"
    ),
    @Index(
      columnList = "print_template_line"
    )
  }
)
public class TemplateContext extends AuditableModel {

  @Id
  @EntitySequence(
    name = "MESSAGE_TEMPLATE_CONTEXT_SEQ"
  )
  private Long id;

  @Widget(
    title = "Name"
  )
  private String name;

  @Widget(
    title = "Expr. value"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Column(
    length = Length.LONG32
  )
  private String value;

  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Template template;

  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private PrintTemplateLine printTemplateLine;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public TemplateContext() {
  }

  public TemplateContext(String name) {
    this.name = name;
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Template getTemplate() {
    return template;
  }

  public void setTemplate(Template template) {
    this.template = template;
  }

  public PrintTemplateLine getPrintTemplateLine() {
    return printTemplateLine;
  }

  public void setPrintTemplateLine(PrintTemplateLine printTemplateLine) {
    this.printTemplateLine = printTemplateLine;
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
    if (!(obj instanceof TemplateContext)) return false;

    final TemplateContext other = (TemplateContext) obj;
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
      .add("name", getName())
      .omitNullValues()
      .toString();
  }
}
