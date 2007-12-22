/*
 *  Copyright 2007, Plutext Pty Ltd.
 *   
 *  This file is part of Docx4J.

    Docx4J is free software: you can redistribute it and/or modify
    it under the terms of version 3 of the GNU General Public License 
    as published by the Free Software Foundation.

    Docx4J is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License   
    along with Docx4J.  If not, see <http://www.gnu.org/licenses/>.
    
 */


package org.docx4j.openpackaging.packages;

import org.apache.log4j.Logger;

import org.docx4j.openpackaging.Base;
import org.docx4j.openpackaging.URIHelper;
import org.docx4j.openpackaging.contenttype.ContentTypeManager;
import org.docx4j.openpackaging.contenttype.ContentTypeManagerImpl;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.DocPropsCorePart;
import org.docx4j.openpackaging.parts.DocPropsCustomPart;
import org.docx4j.openpackaging.parts.DocPropsExtendedPart;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.Parts;
import org.docx4j.openpackaging.parts.relationships.Namespaces;
import org.docx4j.openpackaging.parts.relationships.RelationshipsPart;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;


/**
 * Represent a Package as defined in the Open Packaging Specification.
 * 
 * @author Jason Harrop
 * @version 0.1
 */
public class Package extends Base {

	private static Logger log = Logger.getLogger(Package.class);

	/**
	 * Package parts collection.  This is a collection of _all_
	 * parts in the package (_except_ relationship parts), 
	 * not just those referred to by the package-level relationships.
	 */
	protected Parts parts = new Parts();

	/**
	 * Retrieve the Parts object.
	 */
	public Parts getParts() {

		return parts;		
	}
	
	protected ContentTypeManager contentTypeManager;

	public ContentTypeManager getContentTypeManager() {
		return contentTypeManager;
	}

	public void setContentTypeManager(ContentTypeManager contentTypeManager) {
		this.contentTypeManager = contentTypeManager;
	}
	
	
	/**
	 * Constructor.
	 * 
	 * Note that Load.createPackage() returns appropriate subclass eg 
	 * WordprocessingMLPackage.
	 * 
	 */
	public Package() {
		try {
			partName = new PartName("/", false);
		} catch (Exception e) {
			log.error(e.getMessage());
			// TODO: handle exception
		}
	}
	
	public Package getPackage() {
		return this;
	}
		
	
	protected DocPropsCorePart docPropsCorePart;

	protected DocPropsExtendedPart docPropsExtendedPart;
	
	protected DocPropsCustomPart docPropsCustomPart;

	@Override
	public boolean setPartShortcut(Part part, String relationshipType) {
		if (relationshipType.equals(Namespaces.PROPERTIES_CORE)) {
			docPropsCorePart = (DocPropsCorePart)part;
			return true;			
		} else if (relationshipType.equals(Namespaces.PROPERTIES_EXTENDED)) {
			docPropsExtendedPart = (DocPropsExtendedPart)part;
			return true;			
		} else {	
			return false;
		}
	}

	public DocPropsCorePart getDocPropsCorePart() {
		return docPropsCorePart;
	}

	public DocPropsExtendedPart getDocPropsExtendedPart() {
		return docPropsExtendedPart;
	}

	public DocPropsCustomPart getDocPropsCustomPart() {
		return docPropsCustomPart;
	}




}
