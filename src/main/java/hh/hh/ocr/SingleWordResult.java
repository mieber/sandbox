package hh.hh.ocr;

public class SingleWordResult {

	private String text;

	private float confidence;

	public SingleWordResult() {
		super();
	}

	public SingleWordResult(String text, float confidence) {
		super();
		this.text = text;
		this.confidence = confidence;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public float getConfidence() {
		return confidence;
	}

	public void setConfidence(float confidence) {
		this.confidence = confidence;
	}

	@Override
	public String toString() {
		return text + " (" + confidence + ")";
	}

}