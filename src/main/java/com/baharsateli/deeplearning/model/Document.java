/*
 * Named Entity Embedding
 * Copyright (C) 2018, Bahar Sateli
 *
 * This file is part of a free software: you can redistribute and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.baharsateli.deeplearning.model;

import java.util.ArrayList;
import java.util.List;

/**
 * POJO class for documents read from a KB or index.
 * @author Bahar Sateli (@baharsateli)
 */
public class Document {

    public Document(String uri) {
        this.uri = uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public String getUri() {
        return uri;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    private String uri;

    private List<Entity> entities = new ArrayList<Entity>();

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("Document\n\tURI: " + this.uri + "\n");
        sb.append("Annotations\n");
        for(final Entity re : this.entities){
            sb.append("\tURI: " + re.getUri() + "\n");
            sb.append("\tType: " + re.getType() + "\n");
            sb.append("\tContent: " + re.getContent() + "\n");
        }
        return sb.toString();
    }
}
