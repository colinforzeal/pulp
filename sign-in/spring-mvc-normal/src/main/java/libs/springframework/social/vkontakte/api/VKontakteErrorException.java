/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package libs.springframework.social.vkontakte.api;

import org.springframework.social.OperationNotPermittedException;

import java.lang.*;

/**
 * VKontakte returned error.
 * @author vkolodrevskiy
 */
@SuppressWarnings("serial")
public class VKontakteErrorException extends OperationNotPermittedException {
    private java.lang.Error error;

	public VKontakteErrorException(java.lang.Error error) {
		super("vkontakte", "VKontakte returned error_code = [" + error.getMessage() + "] error_msg = [" + error.getMessage() + "]");
        this.error = error;
    }

    public java.lang.Error getError() {
        return error;
    }
}
