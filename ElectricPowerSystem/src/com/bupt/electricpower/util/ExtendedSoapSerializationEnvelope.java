package com.bupt.electricpower.util;

import java.io.IOException;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.xmlpull.v1.XmlSerializer;

public class ExtendedSoapSerializationEnvelope extends
		SoapSerializationEnvelope {

	public ExtendedSoapSerializationEnvelope(int version) {
		super(version);
	}

	@Override
	protected void writeProperty(XmlSerializer writer, Object obj,
			PropertyInfo type) throws IOException {
		super.writeProperty(writer, obj, type);
		if (obj == null) {
			writer.attribute(xsi, "nil", "true");
			/*
			 * if (!(obj instanceof SoapObject)) {
			 * 
			 * //writer.attribute(xsi, version >= VER11 ? "nil" : "null",
			 * "true"); } else { writer.attribute(xsi, "nil", "true");
			 * //writer.attribute(xsi, version >= VER12 ? "nil" : "null",
			 * "true"); }
			 */
			return;
		}
	}
}