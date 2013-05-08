package com.cts.processor;

import java.util.List;

import com.cts.dal.ZipCodeLookUpDAO;
import com.cts.dal.ZipLocationBean;
import com.cts.processor.client.TrackingProvider;
import com.cts.processor.datamodel.Address;
import com.cts.processor.datamodel.ShipmentTrackingRequest;
import com.cts.processor.datamodel.ShipmentTrackingResponse;
import com.cts.processor.datamodel.TrackingEvent;

public class TrackingServiceProcessor {

	private static TrackingServiceProcessor m_self = new TrackingServiceProcessor();

	// Private constructor in order to make singleton.
	private TrackingServiceProcessor() {

	}

	public static TrackingServiceProcessor getInstance() {
		return m_self;
	}

	public ShipmentTrackingResponse getStatus(ShipmentTrackingRequest request) {

		TrackingProvider provide = TrackingSourceProviderFactory
				.getTrackingProvider(request);
		ShipmentTrackingResponse response = provide.getTracking(request);
		processForZipLocation(response);
		return response;
	}

	private void processForZipLocation(ShipmentTrackingResponse response) {
		if (response == null) {
			return;
		}
		Address destinationAddress = response.getDestinationAddress();
		if (destinationAddress != null) {
			updateLongitude(destinationAddress);
		}
		List<TrackingEvent> eventList = response.getTrackingEvents();

		if (eventList == null) {
			return;
		}

		for (TrackingEvent event : eventList) {
			if (event == null) {
				continue;
			}
			Address eventAddress = event.getAddress();
			if(eventAddress != null){
				updateLongitude(eventAddress);
			}
		}
	}

	private void updateLongitude(Address address) {
		if (address == null) {
			return;
		}
		String postalCode = address.getPostalCode();

		if (postalCode != null && postalCode.length() > 0) {
			ZipLocationBean bean = ZipCodeLookUpDAO.findByZipCode(postalCode);
			if (bean != null) {
				address.setLatitude(bean.getLatitude());
				address.setLongitude(bean.getLongitude());
			}
		}
	}
}