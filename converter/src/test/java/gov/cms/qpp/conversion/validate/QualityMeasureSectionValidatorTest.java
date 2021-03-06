package gov.cms.qpp.conversion.validate;

import static com.google.common.truth.Truth.assertWithMessage;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import gov.cms.qpp.conversion.model.Node;
import gov.cms.qpp.conversion.model.TemplateId;
import gov.cms.qpp.conversion.model.error.Detail;
import gov.cms.qpp.conversion.model.error.ErrorCode;
import gov.cms.qpp.conversion.model.error.correspondence.DetailsErrorEquals;

public class QualityMeasureSectionValidatorTest {
	private Node reportingParameterNode;
	private Node qualityMeasureSectionNode;

	@Before
	public void setUpQualityMeasureSection() {
		reportingParameterNode = new Node(TemplateId.REPORTING_PARAMETERS_ACT);
		qualityMeasureSectionNode = new Node(TemplateId.MEASURE_SECTION_V2);
	}

	@Test
	public void validQualityMeasureSectionValidation() {
		qualityMeasureSectionNode.addChildNode(reportingParameterNode);

		Set<Detail> errors = validateQualityMeasureSection();

		assertWithMessage("Must not contain errors")
				.that(errors).isEmpty();
	}

	@Test
	public void testMissingReportingParams() {
		Set<Detail> errors = validateQualityMeasureSection();

		assertWithMessage("Must contain correct error")
				.that(errors)
				.comparingElementsUsing(DetailsErrorEquals.INSTANCE)
				.containsExactly(ErrorCode.QUALITY_MEASURE_SECTION_REQUIRED_REPORTING_PARAM_REQUIREMENT);
	}

	@Test
	public void testTooManyReportingParams() {
		Node secondReportingParameterNode = new Node(TemplateId.REPORTING_PARAMETERS_ACT);
		qualityMeasureSectionNode.addChildNodes(reportingParameterNode, secondReportingParameterNode);

		Set<Detail> errors = validateQualityMeasureSection();

		assertWithMessage("Must contain correct error")
				.that(errors)
				.comparingElementsUsing(DetailsErrorEquals.INSTANCE)
				.containsExactly(ErrorCode.QUALITY_MEASURE_SECTION_REQUIRED_REPORTING_PARAM_REQUIREMENT);
	}

	private Set<Detail> validateQualityMeasureSection() {
		QualityMeasureSectionValidator validator = new QualityMeasureSectionValidator();
		validator.internalValidateSingleNode(qualityMeasureSectionNode);
		return validator.getDetails();
	}
}
