/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.bhuang.avro.model;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class AvroHttpRequest extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 5120237316841536551L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AvroHttpRequest\",\"namespace\":\"com.baeldung.avro.model\",\"fields\":[{\"name\":\"requestTime\",\"type\":\"long\"},{\"name\":\"id\",\"type\":[\"null\",\"string\"]},{\"name\":\"clientIdentifier\",\"type\":{\"type\":\"record\",\"name\":\"ClientIdentifier\",\"fields\":[{\"name\":\"hostName\",\"type\":\"string\"},{\"name\":\"ipAddress\",\"type\":\"string\"}]}},{\"name\":\"employeeNames\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"default\":null},{\"name\":\"active\",\"type\":{\"type\":\"enum\",\"name\":\"Active\",\"symbols\":[\"YES\",\"NO\"]}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<AvroHttpRequest> ENCODER =
      new BinaryMessageEncoder<AvroHttpRequest>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<AvroHttpRequest> DECODER =
      new BinaryMessageDecoder<AvroHttpRequest>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<AvroHttpRequest> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<AvroHttpRequest> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<AvroHttpRequest>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this AvroHttpRequest to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a AvroHttpRequest from a ByteBuffer. */
  public static AvroHttpRequest fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public long requestTime;
  @Deprecated public java.lang.CharSequence id;
  @Deprecated public com.bhuang.avro.model.ClientIdentifier clientIdentifier;
  @Deprecated public java.util.List<java.lang.CharSequence> employeeNames;
  @Deprecated public com.bhuang.avro.model.Active active;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public AvroHttpRequest() {}

  /**
   * All-args constructor.
   * @param requestTime The new value for requestTime
   * @param id The new value for id
   * @param clientIdentifier The new value for clientIdentifier
   * @param employeeNames The new value for employeeNames
   * @param active The new value for active
   */
  public AvroHttpRequest(java.lang.Long requestTime, java.lang.CharSequence id, com.bhuang.avro.model.ClientIdentifier clientIdentifier, java.util.List<java.lang.CharSequence> employeeNames, com.bhuang.avro.model.Active active) {
    this.requestTime = requestTime;
    this.id = id;
    this.clientIdentifier = clientIdentifier;
    this.employeeNames = employeeNames;
    this.active = active;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return requestTime;
    case 1: return id;
    case 2: return clientIdentifier;
    case 3: return employeeNames;
    case 4: return active;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: requestTime = (java.lang.Long)value$; break;
    case 1: id = (java.lang.CharSequence)value$; break;
    case 2: clientIdentifier = (com.bhuang.avro.model.ClientIdentifier)value$; break;
    case 3: employeeNames = (java.util.List<java.lang.CharSequence>)value$; break;
    case 4: active = (com.bhuang.avro.model.Active)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'requestTime' field.
   * @return The value of the 'requestTime' field.
   */
  public java.lang.Long getRequestTime() {
    return requestTime;
  }

  /**
   * Sets the value of the 'requestTime' field.
   * @param value the value to set.
   */
  public void setRequestTime(java.lang.Long value) {
    this.requestTime = value;
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.lang.CharSequence getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.CharSequence value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'clientIdentifier' field.
   * @return The value of the 'clientIdentifier' field.
   */
  public com.bhuang.avro.model.ClientIdentifier getClientIdentifier() {
    return clientIdentifier;
  }

  /**
   * Sets the value of the 'clientIdentifier' field.
   * @param value the value to set.
   */
  public void setClientIdentifier(com.bhuang.avro.model.ClientIdentifier value) {
    this.clientIdentifier = value;
  }

  /**
   * Gets the value of the 'employeeNames' field.
   * @return The value of the 'employeeNames' field.
   */
  public java.util.List<java.lang.CharSequence> getEmployeeNames() {
    return employeeNames;
  }

  /**
   * Sets the value of the 'employeeNames' field.
   * @param value the value to set.
   */
  public void setEmployeeNames(java.util.List<java.lang.CharSequence> value) {
    this.employeeNames = value;
  }

  /**
   * Gets the value of the 'active' field.
   * @return The value of the 'active' field.
   */
  public com.bhuang.avro.model.Active getActive() {
    return active;
  }

  /**
   * Sets the value of the 'active' field.
   * @param value the value to set.
   */
  public void setActive(com.bhuang.avro.model.Active value) {
    this.active = value;
  }

  /**
   * Creates a new AvroHttpRequest RecordBuilder.
   * @return A new AvroHttpRequest RecordBuilder
   */
  public static com.bhuang.avro.model.AvroHttpRequest.Builder newBuilder() {
    return new com.bhuang.avro.model.AvroHttpRequest.Builder();
  }

  /**
   * Creates a new AvroHttpRequest RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new AvroHttpRequest RecordBuilder
   */
  public static com.bhuang.avro.model.AvroHttpRequest.Builder newBuilder(com.bhuang.avro.model.AvroHttpRequest.Builder other) {
    return new com.bhuang.avro.model.AvroHttpRequest.Builder(other);
  }

  /**
   * Creates a new AvroHttpRequest RecordBuilder by copying an existing AvroHttpRequest instance.
   * @param other The existing instance to copy.
   * @return A new AvroHttpRequest RecordBuilder
   */
  public static com.bhuang.avro.model.AvroHttpRequest.Builder newBuilder(com.bhuang.avro.model.AvroHttpRequest other) {
    return new com.bhuang.avro.model.AvroHttpRequest.Builder(other);
  }

  /**
   * RecordBuilder for AvroHttpRequest instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<AvroHttpRequest>
    implements org.apache.avro.data.RecordBuilder<AvroHttpRequest> {

    private long requestTime;
    private java.lang.CharSequence id;
    private com.bhuang.avro.model.ClientIdentifier clientIdentifier;
    private com.bhuang.avro.model.ClientIdentifier.Builder clientIdentifierBuilder;
    private java.util.List<java.lang.CharSequence> employeeNames;
    private com.bhuang.avro.model.Active active;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.bhuang.avro.model.AvroHttpRequest.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.requestTime)) {
        this.requestTime = data().deepCopy(fields()[0].schema(), other.requestTime);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.id)) {
        this.id = data().deepCopy(fields()[1].schema(), other.id);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.clientIdentifier)) {
        this.clientIdentifier = data().deepCopy(fields()[2].schema(), other.clientIdentifier);
        fieldSetFlags()[2] = true;
      }
      if (other.hasClientIdentifierBuilder()) {
        this.clientIdentifierBuilder = com.bhuang.avro.model.ClientIdentifier.newBuilder(other.getClientIdentifierBuilder());
      }
      if (isValidValue(fields()[3], other.employeeNames)) {
        this.employeeNames = data().deepCopy(fields()[3].schema(), other.employeeNames);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.active)) {
        this.active = data().deepCopy(fields()[4].schema(), other.active);
        fieldSetFlags()[4] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing AvroHttpRequest instance
     * @param other The existing instance to copy.
     */
    private Builder(com.bhuang.avro.model.AvroHttpRequest other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.requestTime)) {
        this.requestTime = data().deepCopy(fields()[0].schema(), other.requestTime);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.id)) {
        this.id = data().deepCopy(fields()[1].schema(), other.id);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.clientIdentifier)) {
        this.clientIdentifier = data().deepCopy(fields()[2].schema(), other.clientIdentifier);
        fieldSetFlags()[2] = true;
      }
      this.clientIdentifierBuilder = null;
      if (isValidValue(fields()[3], other.employeeNames)) {
        this.employeeNames = data().deepCopy(fields()[3].schema(), other.employeeNames);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.active)) {
        this.active = data().deepCopy(fields()[4].schema(), other.active);
        fieldSetFlags()[4] = true;
      }
    }

    /**
      * Gets the value of the 'requestTime' field.
      * @return The value.
      */
    public java.lang.Long getRequestTime() {
      return requestTime;
    }

    /**
      * Sets the value of the 'requestTime' field.
      * @param value The value of 'requestTime'.
      * @return This builder.
      */
    public com.bhuang.avro.model.AvroHttpRequest.Builder setRequestTime(long value) {
      validate(fields()[0], value);
      this.requestTime = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'requestTime' field has been set.
      * @return True if the 'requestTime' field has been set, false otherwise.
      */
    public boolean hasRequestTime() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'requestTime' field.
      * @return This builder.
      */
    public com.bhuang.avro.model.AvroHttpRequest.Builder clearRequestTime() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.CharSequence getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public com.bhuang.avro.model.AvroHttpRequest.Builder setId(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.id = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public com.bhuang.avro.model.AvroHttpRequest.Builder clearId() {
      id = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'clientIdentifier' field.
      * @return The value.
      */
    public com.bhuang.avro.model.ClientIdentifier getClientIdentifier() {
      return clientIdentifier;
    }

    /**
      * Sets the value of the 'clientIdentifier' field.
      * @param value The value of 'clientIdentifier'.
      * @return This builder.
      */
    public com.bhuang.avro.model.AvroHttpRequest.Builder setClientIdentifier(com.bhuang.avro.model.ClientIdentifier value) {
      validate(fields()[2], value);
      this.clientIdentifierBuilder = null;
      this.clientIdentifier = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'clientIdentifier' field has been set.
      * @return True if the 'clientIdentifier' field has been set, false otherwise.
      */
    public boolean hasClientIdentifier() {
      return fieldSetFlags()[2];
    }

    /**
     * Gets the Builder instance for the 'clientIdentifier' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public com.bhuang.avro.model.ClientIdentifier.Builder getClientIdentifierBuilder() {
      if (clientIdentifierBuilder == null) {
        if (hasClientIdentifier()) {
          setClientIdentifierBuilder(com.bhuang.avro.model.ClientIdentifier.newBuilder(clientIdentifier));
        } else {
          setClientIdentifierBuilder(com.bhuang.avro.model.ClientIdentifier.newBuilder());
        }
      }
      return clientIdentifierBuilder;
    }

    /**
     * Sets the Builder instance for the 'clientIdentifier' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public com.bhuang.avro.model.AvroHttpRequest.Builder setClientIdentifierBuilder(com.bhuang.avro.model.ClientIdentifier.Builder value) {
      clearClientIdentifier();
      clientIdentifierBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'clientIdentifier' field has an active Builder instance
     * @return True if the 'clientIdentifier' field has an active Builder instance
     */
    public boolean hasClientIdentifierBuilder() {
      return clientIdentifierBuilder != null;
    }

    /**
      * Clears the value of the 'clientIdentifier' field.
      * @return This builder.
      */
    public com.bhuang.avro.model.AvroHttpRequest.Builder clearClientIdentifier() {
      clientIdentifier = null;
      clientIdentifierBuilder = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'employeeNames' field.
      * @return The value.
      */
    public java.util.List<java.lang.CharSequence> getEmployeeNames() {
      return employeeNames;
    }

    /**
      * Sets the value of the 'employeeNames' field.
      * @param value The value of 'employeeNames'.
      * @return This builder.
      */
    public com.bhuang.avro.model.AvroHttpRequest.Builder setEmployeeNames(java.util.List<java.lang.CharSequence> value) {
      validate(fields()[3], value);
      this.employeeNames = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'employeeNames' field has been set.
      * @return True if the 'employeeNames' field has been set, false otherwise.
      */
    public boolean hasEmployeeNames() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'employeeNames' field.
      * @return This builder.
      */
    public com.bhuang.avro.model.AvroHttpRequest.Builder clearEmployeeNames() {
      employeeNames = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'active' field.
      * @return The value.
      */
    public com.bhuang.avro.model.Active getActive() {
      return active;
    }

    /**
      * Sets the value of the 'active' field.
      * @param value The value of 'active'.
      * @return This builder.
      */
    public com.bhuang.avro.model.AvroHttpRequest.Builder setActive(com.bhuang.avro.model.Active value) {
      validate(fields()[4], value);
      this.active = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'active' field has been set.
      * @return True if the 'active' field has been set, false otherwise.
      */
    public boolean hasActive() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'active' field.
      * @return This builder.
      */
    public com.bhuang.avro.model.AvroHttpRequest.Builder clearActive() {
      active = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AvroHttpRequest build() {
      try {
        AvroHttpRequest record = new AvroHttpRequest();
        record.requestTime = fieldSetFlags()[0] ? this.requestTime : (java.lang.Long) defaultValue(fields()[0]);
        record.id = fieldSetFlags()[1] ? this.id : (java.lang.CharSequence) defaultValue(fields()[1]);
        if (clientIdentifierBuilder != null) {
          record.clientIdentifier = this.clientIdentifierBuilder.build();
        } else {
          record.clientIdentifier = fieldSetFlags()[2] ? this.clientIdentifier : (com.bhuang.avro.model.ClientIdentifier) defaultValue(fields()[2]);
        }
        record.employeeNames = fieldSetFlags()[3] ? this.employeeNames : (java.util.List<java.lang.CharSequence>) defaultValue(fields()[3]);
        record.active = fieldSetFlags()[4] ? this.active : (com.bhuang.avro.model.Active) defaultValue(fields()[4]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<AvroHttpRequest>
    WRITER$ = (org.apache.avro.io.DatumWriter<AvroHttpRequest>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<AvroHttpRequest>
    READER$ = (org.apache.avro.io.DatumReader<AvroHttpRequest>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
