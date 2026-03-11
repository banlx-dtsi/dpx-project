package com.axelor.meta.db;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.VirtualColumn;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.db.hibernate.type.ValueEnumType;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import org.hibernate.Length;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(
  name = "META_THEME",
  indexes = {
    @Index(
      columnList = "name"
    ),
    @Index(
      columnList = "fullname"
    )
  }
)
public class MetaTheme extends AuditableModel {

  @Id
  @EntitySequence(
    name = "META_THEME_SEQ"
  )
  private Long id;

  @Widget(
    help = "Define a name for the theme. <b>light</b> or <b>dark</b> can be used to override built-in themes."
  )
  @NotNull
  private String name;

  @Widget(
    title = "Available for users",
    help = "Allow this theme to be used by users"
  )
  private Boolean isSelectable = Boolean.FALSE;

  @Widget(
    title = "Label"
  )
  private String label;

  @NameColumn
  @VirtualColumn
  @Access(AccessType.PROPERTY)
  private String fullname;

  @Widget(
    title = "Content"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Column(
    length = Length.LONG32
  )
  private String content;

  @Widget(
    title = "Logo mode"
  )
  @Basic
  @Type(ValueEnumType.class)
  private ThemeLogoMode logoMode = ThemeLogoMode.NONE;

  public MetaTheme() {
  }

  public MetaTheme(String name) {
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

  /**
   * Define a name for the theme. <b>light</b> or <b>dark</b> can be used to override built-in themes.
   *
   * @return the property value
   */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Allow this theme to be used by users
   *
   * @return the property value
   */
  public Boolean getIsSelectable() {
    return isSelectable == null ? Boolean.FALSE : isSelectable;
  }

  public void setIsSelectable(Boolean isSelectable) {
    this.isSelectable = isSelectable;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getFullname() {
    try {
      fullname = computeFullname();
    } catch (NullPointerException e) {
      Logger logger = LoggerFactory.getLogger(getClass());
      logger.error("NPE in function field: getFullname()", e);
    }
    return fullname;
  }

  protected String computeFullname() {
    String text = "[" + name + "]";
        return label == null ? text : text + " - " + label;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public ThemeLogoMode getLogoMode() {
    return logoMode;
  }

  public void setLogoMode(ThemeLogoMode logoMode) {
    this.logoMode = logoMode;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (this == obj) return true;
    if (!(obj instanceof MetaTheme)) return false;

    final MetaTheme other = (MetaTheme) obj;
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
      .add("isSelectable", getIsSelectable())
      .add("label", getLabel())
      .omitNullValues()
      .toString();
  }
}
