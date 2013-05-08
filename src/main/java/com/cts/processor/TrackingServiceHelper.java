package com.cts.processor;

import com.cts.processor.datamodel.ShipmentTrackingResponse;

public class TrackingServiceHelper {

	private static TrackingServiceHelper m_self = new TrackingServiceHelper();

	// Private constructor in order to make singleton.
	private TrackingServiceHelper() {

	}

	public static TrackingServiceHelper getInstance() {
		return m_self;
	}

	public ShipmentTrackingResponse getDefaultResponse() {
		ShipmentTrackingResponse response = new ShipmentTrackingResponse();
		return response;
	}
}