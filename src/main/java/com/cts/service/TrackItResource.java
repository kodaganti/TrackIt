package com.cts.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.cts.processor.TrackingServiceProcessor;
import com.cts.processor.datamodel.ShipmentTrackingRequest;
import com.cts.processor.datamodel.ShipmentTrackingResponse;

@Path("/trackIt")
public class TrackItResource {

	@GET
	@Path("/{trackingNumber}")
	@Produces("application/json")
	public ShipmentTrackingResponse trackPackage(
			@PathParam("trackingNumber") String trackingNumber) {
		ShipmentTrackingRequest request = new ShipmentTrackingRequest();
		request.setTrackingNumber(trackingNumber);
		return TrackingServiceProcessor.getInstance().getStatus(request);
	}

	@GET
	@Path("/{trackingNumber}/{carrier}")
	@Produces("application/json")
	public ShipmentTrackingResponse trackPackage(
			@PathParam("trackingNumber") String trackingNumber,
			@PathParam("carrier") String carrier) {
		ShipmentTrackingRequest request = new ShipmentTrackingRequest();
		request.setTrackingNumber(trackingNumber);
		request.setCarrier(carrier);
		return TrackingServiceProcessor.getInstance().getStatus(request);
	}
}