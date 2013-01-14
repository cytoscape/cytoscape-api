package org.cytoscape.view.model;

/**
 * Defines a continuous range of values for {@link VisualProperty}s.
 *
 * @param <T> The generic type of this ContinuousRange.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule viewmodel-api
 */
public class ContinuousRange<T> implements Range<T> {

	private final Class<T> type;
	
	private final T min;
	private final T max;
	
	private final Boolean includeMin;
	private final Boolean includeMax;
	

	/**
	 * Constructs this ContinuousRange.
	 * @param type the type of this ContinuousRange.
	 * @param min the minimum value of this range.
	 * @param max the maximum value of this range.
	 * @param includeMin Whether or not to include the minimum in the range.
	 * @param includeMax Whether or not to include the maximum in the range.
	 */
	public ContinuousRange(final Class<T> type, final T min, final T max, final Boolean includeMin, final Boolean includeMax) {
		this.type = type;
		this.min = min;
		this.max = max;
		this.includeMin = includeMin;
		this.includeMax = includeMax;
	}
	
	@Override
	public Class<T> getType() {
		return type;
	}

	@Override
	public boolean isDiscrete() {
		return false;
	}

	/**
	 * Returns the minimum value of this range.
	 * @return the minimum value of this range.
	 */
	public T getMin() {
		return min;
	}

	/**
	 * Returns the maximum value of this range.
	 * @return the maximum value of this range.
	 */
	public T getMax() {
		return max;
	}

	/**
	 * Returns true if the minimum value is included in this range.
	 * @return true if the minimum value is included in this range.
	 */
	public boolean includeMin() {
		return includeMin;
	}

	/**
	 * Returns true if the maximum value is included in this range.
	 * @return true if the maximum value is included in this range.
	 */
	public boolean includeMax() {
		return includeMax;
	}

	@Override
	public boolean inRange(T value) {
		// By default, treat T as number.  Otherwise, it should be implemented by developer.
		if(value instanceof Number && min instanceof Number && max instanceof Number) {
			return validateNumber((Number)value, (Number)min, (Number)max);
		} else {
			return true;
		}
			
	}
	
	
	private boolean validateNumber(final Number value, final Number minNumber, final Number maxNumber) {
		final double testValue = value.doubleValue();
		final double doubleMin = minNumber.doubleValue();
		final double doubleMax = maxNumber.doubleValue();
		
		if(includeMax && includeMin) {
			if(doubleMin <= testValue && doubleMax >= testValue )
				return true;
			else
				return false;
		} else if(includeMax == false && includeMin == false) {
			if(doubleMin < testValue && doubleMax > testValue )
				return true;
			else
				return false;
		} else if(includeMax == false && includeMin) {
			if(doubleMin <= testValue && doubleMax > testValue )
				return true;
			else
				return false;
		} else {
			if(doubleMin < testValue && doubleMax >= testValue )
				return true;
			else
				return false;
		}
	}

}
