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
/**
 * POJO class for named entities.
 * @author Bahar Sateli (@baharsateli)
 */
public class Entity {

    private String uri;
    private String type;

    public Entity(String uri, String type, String content) {
        this.uri = uri;
        this.type = type;
        this.content = content;
    }

    private String content;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("Entity\n\tURI: " + this.uri + "\n");
        sb.append("\tContent: " + this.content + "\n");
        return sb.toString();
    }
}
