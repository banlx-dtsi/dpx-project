package com.axelor.apps.base.db;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.Track;
import com.axelor.db.annotations.TrackEvent;
import com.axelor.db.annotations.TrackField;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.db.hibernate.type.JsonType;
import com.axelor.db.hibernate.type.ValueEnumType;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.annotations.Type;

@Entity
@Table(
  name = "BASE_SEQUENCE",
  uniqueConstraints = @UniqueConstraint(
    columnNames = {
      "company",
      "codeSelect",
      "prefixe",
      "suffixe"
    }
  ),
  indexes = {
    @Index(
      columnList = "codeSelect, company",
      name = "idx_sequence_code_company"
    ),
    @Index(
      columnList = "company"
    ),
    @Index(
      columnList = "name"
    ),
    @Index(
      columnList = "fullName"
    )
  }
)
@Track(
  on = TrackEvent.UPDATE,
  fields = {
    @TrackField(
      name = "codeSelect"
    ),
    @TrackField(
      name = "company"
    ),
    @TrackField(
      name = "sequenceTypeSelect"
    ),
    @TrackField(
      name = "padding"
    ),
    @TrackField(
      name = "toBeAdded"
    ),
    @TrackField(
      name = "prefixe"
    ),
    @TrackField(
      name = "suffixe"
    ),
    @TrackField(
      name = "yearlyResetOk"
    ),
    @TrackField(
      name = "monthlyResetOk"
    )
  }
)
public class Sequence extends AuditableModel {

  @Id
  @EntitySequence(
    name = "BASE_SEQUENCE_SEQ"
  )
  private Long id;

  @Widget(
    title = "Company"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Company company;

  @Widget(
    title = "Name"
  )
  @NotNull
  private String name;

  @Widget(
    title = "Document concerned",
    selection = "sequence.generic.code.select"
  )
  @NotNull
  private String codeSelect;

  @Widget(
    title = "Sequence type"
  )
  @Basic
  @Type(ValueEnumType.class)
  @NotNull
  private SequenceTypeSelect sequenceTypeSelect = SequenceTypeSelect.NUMBERS;

  @Widget(
    title = "Uppercase / lowercase"
  )
  @Basic
  @Type(ValueEnumType.class)
  private SequenceLettersTypeSelect sequenceLettersTypeSelect = SequenceLettersTypeSelect.UPPERCASE;

  @Widget(
    title = "Prefix"
  )
  private String prefixe;

  @Widget(
    title = "Define prefix with a script"
  )
  private Boolean prefixGroovyOk = Boolean.FALSE;

  @Widget(
    title = "Define suffix with a script"
  )
  private Boolean suffixGroovyOk = Boolean.FALSE;

  @Widget(
    title = "Suffix"
  )
  private String suffixe;

  @Widget(
    title = "Prefix"
  )
  private String prefixGroovy;

  @Widget(
    title = "Suffix"
  )
  private String suffixGroovy;

  @Widget(
    title = "Padding"
  )
  @NotNull
  private Integer padding = 0;

  @Widget(
    title = "Pattern"
  )
  private String pattern;

  @Widget(
    title = "Increment"
  )
  @NotNull
  @Min(1)
  private Integer toBeAdded = 0;

  @Widget(
    title = "Yearly reset"
  )
  private Boolean yearlyResetOk = Boolean.FALSE;

  @Widget(
    title = "Monthly reset"
  )
  private Boolean monthlyResetOk = Boolean.FALSE;

  @Widget(
    title = "Versions"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "sequence",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<SequenceVersion> sequenceVersionList;

  @Widget(
    title = "Full name"
  )
  @NameColumn
  private String fullName;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public Sequence() {
  }

  public Sequence(String name) {
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

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCodeSelect() {
    return codeSelect;
  }

  public void setCodeSelect(String codeSelect) {
    this.codeSelect = codeSelect;
  }

  public SequenceTypeSelect getSequenceTypeSelect() {
    return sequenceTypeSelect;
  }

  public void setSequenceTypeSelect(SequenceTypeSelect sequenceTypeSelect) {
    this.sequenceTypeSelect = sequenceTypeSelect;
  }

  public SequenceLettersTypeSelect getSequenceLettersTypeSelect() {
    return sequenceLettersTypeSelect;
  }

  public void setSequenceLettersTypeSelect(SequenceLettersTypeSelect sequenceLettersTypeSelect) {
    this.sequenceLettersTypeSelect = sequenceLettersTypeSelect;
  }

  public String getPrefixe() {
    return prefixe;
  }

  public void setPrefixe(String prefixe) {
    this.prefixe = prefixe;
  }

  public Boolean getPrefixGroovyOk() {
    return prefixGroovyOk == null ? Boolean.FALSE : prefixGroovyOk;
  }

  public void setPrefixGroovyOk(Boolean prefixGroovyOk) {
    this.prefixGroovyOk = prefixGroovyOk;
  }

  public Boolean getSuffixGroovyOk() {
    return suffixGroovyOk == null ? Boolean.FALSE : suffixGroovyOk;
  }

  public void setSuffixGroovyOk(Boolean suffixGroovyOk) {
    this.suffixGroovyOk = suffixGroovyOk;
  }

  public String getSuffixe() {
    return suffixe;
  }

  public void setSuffixe(String suffixe) {
    this.suffixe = suffixe;
  }

  public String getPrefixGroovy() {
    return prefixGroovy;
  }

  public void setPrefixGroovy(String prefixGroovy) {
    this.prefixGroovy = prefixGroovy;
  }

  public String getSuffixGroovy() {
    return suffixGroovy;
  }

  public void setSuffixGroovy(String suffixGroovy) {
    this.suffixGroovy = suffixGroovy;
  }

  public Integer getPadding() {
    return padding == null ? 0 : padding;
  }

  public void setPadding(Integer padding) {
    this.padding = padding;
  }

  public String getPattern() {
    return pattern;
  }

  public void setPattern(String pattern) {
    this.pattern = pattern;
  }

  public Integer getToBeAdded() {
    return toBeAdded == null ? 0 : toBeAdded;
  }

  public void setToBeAdded(Integer toBeAdded) {
    this.toBeAdded = toBeAdded;
  }

  public Boolean getYearlyResetOk() {
    return yearlyResetOk == null ? Boolean.FALSE : yearlyResetOk;
  }

  public void setYearlyResetOk(Boolean yearlyResetOk) {
    this.yearlyResetOk = yearlyResetOk;
  }

  public Boolean getMonthlyResetOk() {
    return monthlyResetOk == null ? Boolean.FALSE : monthlyResetOk;
  }

  public void setMonthlyResetOk(Boolean monthlyResetOk) {
    this.monthlyResetOk = monthlyResetOk;
  }

  public List<SequenceVersion> getSequenceVersionList() {
    return sequenceVersionList;
  }

  public void setSequenceVersionList(List<SequenceVersion> sequenceVersionList) {
    this.sequenceVersionList = sequenceVersionList;
  }

  /**
   * Add the given {@link SequenceVersion} item to the {@code sequenceVersionList} collection.
   *
   * <p>
   * It sets {@code item.sequence = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addSequenceVersionListItem(SequenceVersion item) {
    if (getSequenceVersionList() == null) {
      setSequenceVersionList(new ArrayList<>());
    }
    getSequenceVersionList().add(item);
    item.setSequence(this);
  }

  /**
   * Remove the given {@link SequenceVersion} item from the {@code sequenceVersionList} collection.
   *
   * @param item the item to remove
   */
  public void removeSequenceVersionListItem(SequenceVersion item) {
    if (getSequenceVersionList() == null) {
      return;
    }
    getSequenceVersionList().remove(item);
  }

  /**
   * Clear the {@code sequenceVersionList} collection.
   */
  public void clearSequenceVersionList() {
    if (getSequenceVersionList() != null) {
      getSequenceVersionList().clear();
    }
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
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
    if (!(obj instanceof Sequence)) return false;

    final Sequence other = (Sequence) obj;
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
      .add("codeSelect", getCodeSelect())
      .add("prefixe", getPrefixe())
      .add("prefixGroovyOk", getPrefixGroovyOk())
      .add("suffixGroovyOk", getSuffixGroovyOk())
      .add("suffixe", getSuffixe())
      .add("prefixGroovy", getPrefixGroovy())
      .add("suffixGroovy", getSuffixGroovy())
      .add("padding", getPadding())
      .add("pattern", getPattern())
      .omitNullValues()
      .toString();
  }
}
