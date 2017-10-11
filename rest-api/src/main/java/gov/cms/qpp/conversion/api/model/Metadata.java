package gov.cms.qpp.conversion.api.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGenerateStrategy;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedTimestamp;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.encryption.DoNotEncrypt;
import com.google.common.base.Objects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Date;

/**
 * Model to hold conversion metadata. Maps to a table in DynamoDB.
 */
@DynamoDBTable(tableName = "ConversionMetadata")
public class Metadata {
	private String uuid;
	private String tin;  //this field is encrypted
	private String npi;
	private Date createdDate;
	private String apm;
	private Long submissionYear;
	private String submissionLocator;
	private String qppLocator;
	private String fileName;
	private Boolean overallStatus;
	private Boolean conversionStatus;
	private Boolean validationStatus;
	private Boolean cpc;
	private String conversionErrorLocator;
	private String validationErrorLocator;


	/**
	 * The UUID that uniquely identifies this item.
	 *
	 * @return The UUID.
	 */
	@DynamoDBHashKey(attributeName = "Uuid")
	@DynamoDBAutoGeneratedKey
	public String getUuid() {
		return uuid;
	}

	/**
	 * There is no reason to set this manually because it is automatically generated when saved to DynamoDB.
	 *
	 * @param uuid The UUID to use.
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * The date and time when this item was created.
	 *
	 * @return The date and time.
	 */
	@DoNotEncrypt
	@DynamoDBAttribute(attributeName = "CreateDate")
	@DynamoDBAutoGeneratedTimestamp(strategy = DynamoDBAutoGenerateStrategy.CREATE)
	public Date getCreatedDate() {
		Date returnedDate = null;

		if (createdDate != null) {
			returnedDate = new Date(createdDate.getTime());
		}

		return returnedDate;
	}

	/**
	 * There is no reason to set this manually because it is automatically generated when saved to DynamoDB.
	 *
	 * @param createdDate The date and time to use.
	 */
	public void setCreatedDate(Date createdDate) {
		if (createdDate != null) {
			this.createdDate = new Date(createdDate.getTime());
		} else {
			this.createdDate = null;
		}
	}

	/**
	 * The TIN associated with the the conversion.
	 *
	 * @return The TIN.
	 */
	@DynamoDBAttribute(attributeName = "Tin")
	public String getTin() {
		return tin;
	}

	/**
	 * Sets the TIN associated with the the conversion.
	 *
	 * @param tin The TIN to use.
	 */
	public void setTin(String tin) {
		this.tin = tin;
	}

	/**
	 * The NPI associated with the the conversion.
	 *
	 * @return The NPI.
	 */
	@DoNotEncrypt
	@DynamoDBAttribute(attributeName = "Npi")
	public String getNpi() {
		return npi;
	}

	/**
	 * Sets the NPI associated with the the conversion
	 *
	 * @param npi The NPI to use.
	 */
	public void setNpi(String npi) {
		this.npi = npi;
	}

	/**
	 * The APM Entity ID associated with the the conversion. Used with CPC+.
	 *
	 * @return The APM Entity ID.
	 */
	@DoNotEncrypt
	@DynamoDBAttribute(attributeName = "Apm")
	public String getApm() {
		return apm;
	}

	/**
	 * Sets the the APM Entity ID associated with the the conversion. Used with CPC+.
	 *
	 * @param apm The APM Entity ID.
	 */
	public void setApm(String apm) {
		this.apm = apm;
	}

	/**
	 * The submission year associated with the the conversion.
	 *
	 * @return The submission year.
	 */
	@DoNotEncrypt
	@DynamoDBAttribute(attributeName = "SubmissionYear")
	public Long getSubmissionYear() {
		return submissionYear;
	}

	/**
	 * Sets the submission year associated with the the conversion
	 *
	 * @param submissionYear The submission year to use.
	 */
	public void setSubmissionYear(Long submissionYear) {
		this.submissionYear = submissionYear;
	}

	/**
	 * A location to where the submitted file can be found.
	 *
	 * For example, for AWS, this could be an ARN.
	 *
	 * @return The location.
	 */
	@DoNotEncrypt
	@DynamoDBAttribute(attributeName = "SubmissionLocator")
	public String getSubmissionLocator() {
		return submissionLocator;
	}

	/**
	 * Sets a location to where the submitted file can be found.
	 *
	 * @param submissionLocator The location to use.
	 */
	public void setSubmissionLocator(String submissionLocator) {
		this.submissionLocator = submissionLocator;
	}

	/**
	 * A location where the submission QPP can be found.
	 *
	 * For example, for AWS, this could be an ARN.
	 *
	 * @return The location.
	 */
	@DoNotEncrypt
	@DynamoDBAttribute(attributeName = "QPPLocator")
	public String getQppLocator() {
		return qppLocator;
	}

	/**
	 * Sets a location where the submission QPP can be found.
	 *
	 * @param qppLocator The location to use.
	 */
	public void setQppLocator(String qppLocator) {
		this.qppLocator = qppLocator;
	}

	/**
	 * The file name of the file uploaded to the converter.
	 *
	 * @return The file name.
	 */
	@DoNotEncrypt
	@DynamoDBAttribute(attributeName = "FileName")
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name of the file uploaded to the converter.
	 *
	 * @param fileName The file name to use.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * The success or failure of the conversion and the submission validation.
	 *
	 * @return Success or failure.
	 */
	@DoNotEncrypt
	@DynamoDBAttribute(attributeName = "OverallStatus")
	public Boolean getOverallStatus() {
		return overallStatus;
	}

	/**
	 * Sets the overall status of the conversion and submission validation.
	 *
	 * @param overallStatus The overall status to use.
	 */
	public void setOverallStatus(Boolean overallStatus) {
		this.overallStatus = overallStatus;
	}

	/**
	 * The success or failure of only the conversion.
	 *
	 * @return Success or failure.
	 */
	@DoNotEncrypt
	@DynamoDBAttribute(attributeName = "ConversionStatus")
	public Boolean getConversionStatus() {
		return conversionStatus;
	}

	/**
	 * Sets the conversion status.
	 *
	 * @param conversionStatus The conversion status to use.
	 */
	public void setConversionStatus(Boolean conversionStatus) {
		this.conversionStatus = conversionStatus;
	}

	/**
	 * The success or failure of only the submission validation.
	 *
	 * @return Success or failure.
	 */
	@DoNotEncrypt
	@DynamoDBAttribute(attributeName = "ValidationStatus")
	public Boolean getValidationStatus() {
		return validationStatus;
	}

	/**
	 * Sets the submission validation status.
	 *
	 * @param validationStatus The validation status to use.
	 */
	public void setValidationStatus(Boolean validationStatus) {
		this.validationStatus = validationStatus;
	}

	/**
	 * Whether the conversion was for the CPC+ program.
	 *
	 * @return True for a CPC+ conversion, false otherwise.
	 */
	@DoNotEncrypt
	@DynamoDBAttribute(attributeName = "Cpc")
	public Boolean getCpc() {
		return cpc;
	}

	/**
	 * Sets whether the conversion was for the CPC+ program.
	 *
	 * @param cpc A CPC+ conversion or not.
	 */
	public void setCpc(Boolean cpc) {
		this.cpc = cpc;
	}

	/**
	 * A location to where the conversion error JSON can be found.
	 *
	 * For example, for AWS, this could be an ARN.
	 *
	 * @return The location.
	 */
	@DoNotEncrypt
	@DynamoDBAttribute(attributeName = "ConversionErrorLocator")
	public String getConversionErrorLocator() {
		return conversionErrorLocator;
	}

	/**
	 * Sets a location to where the conversion error JSON can be found.
	 *
	 * @param conversionErrorLocator A location.
	 */
	public void setConversionErrorLocator(String conversionErrorLocator) {
		this.conversionErrorLocator = conversionErrorLocator;
	}

	/**
	 * A location to where the submission validation error JSON can be found.
	 *
	 * For example, for AWS, this could be an ARN.
	 *
	 * @return The location.
	 */
	@DoNotEncrypt
	@DynamoDBAttribute(attributeName = "ValidationErrorLocator")
	public String getValidationErrorLocator() {
		return validationErrorLocator;
	}

	/**
	 * Sets a location to where the submission validation error JSON can be found.
	 *
	 * @param validationErrorLocator A location.
	 */
	public void setValidationErrorLocator(String validationErrorLocator) {
		this.validationErrorLocator = validationErrorLocator;
	}

	/**
	 * Determines the equality between this object and another.
	 *
	 * @param o The other object.
	 * @return True if equal, false if not equal.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || o.getClass() != getClass()) {
			return false;
		}

		Metadata that = (Metadata) o;

		boolean equals = Objects.equal(submissionYear, that.submissionYear);
		equals &= Objects.equal(overallStatus, that.overallStatus);
		equals &= Objects.equal(conversionStatus, that.conversionStatus);
		equals &= Objects.equal(validationStatus, that.validationStatus);
		equals &= Objects.equal(cpc, that.cpc);
		equals &= Objects.equal(uuid, that.uuid);
		equals &= Objects.equal(tin, that.tin);
		equals &= Objects.equal(npi, that.npi);
		equals &= Objects.equal(createdDate, that.createdDate);
		equals &= Objects.equal(apm, that.apm);
		equals &= Objects.equal(submissionLocator, that.submissionLocator);
		equals &= Objects.equal(qppLocator, that.qppLocator);
		equals &= Objects.equal(fileName, that.fileName);
		equals &= Objects.equal(conversionErrorLocator, that.conversionErrorLocator);
		equals &= Objects.equal(validationErrorLocator, that.validationErrorLocator);
		return equals;
	}

	/**
	 * Computes and returns the hash code for this object.
	 * @return The hash code.
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(uuid, tin, npi, createdDate, apm, submissionYear,
				submissionLocator, qppLocator, fileName, overallStatus, conversionStatus, validationStatus,
				cpc, conversionErrorLocator, validationErrorLocator);
	}
}
