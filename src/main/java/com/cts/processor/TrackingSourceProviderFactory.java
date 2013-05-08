package com.cts.processor;

import com.cts.processor.client.FedexTrackingProvider;
import com.cts.processor.client.TrackingProvider;
import com.cts.processor.datamodel.ShipmentTrackingRequest;

public class TrackingSourceProviderFactory {

	private static TrackingSourceProviderFactory m_self = new TrackingSourceProviderFactory();

	// Private constructor in order to make singleton.
	private TrackingSourceProviderFactory() {

	}
	
	public static TrackingProvider getTrackingProvider(ShipmentTrackingRequest request){
		return FedexTrackingProvider.getInstance();
	}
}