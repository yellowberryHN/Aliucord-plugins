/*
  Copyright 2019 Dimitry Ivanov (legal@noties.io)
  Modifications: Copyright 2021 Juby210

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/

package io.noties.prism4j;

import androidx.annotation.NonNull;

import java.util.List;

public class GrammarImpl implements Prism4j.Grammar {

    private final String name;
    private final List<Prism4j.Token> tokens;

    public GrammarImpl(@NonNull String name, @NonNull List<Prism4j.Token> tokens) {
        this.name = name;
        this.tokens = tokens;
    }

    @NonNull
    @Override
    public String name() {
        return name;
    }

    @NonNull
    @Override
    public List<Prism4j.Token> tokens() {
        return tokens;
    }

    @NonNull
    @Override
    public String toString() {
        return ToString.toString(this);
    }
}
