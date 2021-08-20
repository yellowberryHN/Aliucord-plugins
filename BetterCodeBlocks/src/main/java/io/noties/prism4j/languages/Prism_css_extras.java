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

package io.noties.prism4j.languages;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;
import static io.noties.prism4j.Prism4j.grammar;
import static io.noties.prism4j.Prism4j.pattern;
import static io.noties.prism4j.Prism4j.token;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.noties.prism4j.GrammarUtils;
import io.noties.prism4j.Prism4j;

@SuppressWarnings("unused")
public class Prism_css_extras {

  @Nullable
  public static Prism4j.Grammar create(@NonNull Prism4j prism4j) {

    final Prism4j.Grammar css = prism4j.grammar("css");

    if (css != null) {

      final Prism4j.Token selector = GrammarUtils.findToken(css, "selector");
      if (selector != null) {
        final Prism4j.Pattern pattern = pattern(
          compile("[^{}\\s][^{}]*(?=\\s*\\{)"),
          false,
          false,
          null,
          grammar("inside",
            token("pseudo-element", pattern(compile(":(?:after|before|first-letter|first-line|selection)|::[-\\w]+"))),
            token("pseudo-class", pattern(compile(":[-\\w]+(?:\\(.*\\))?"))),
            token("class", pattern(compile("\\.[-:.\\w]+"))),
            token("id", pattern(compile("#[-:.\\w]+"))),
            token("attribute", pattern(compile("\\[[^\\]]+\\]")))
          )
        );
        selector.patterns().clear();
        selector.patterns().add(pattern);
      }

      GrammarUtils.insertBeforeToken(css, "function",
        token("hexcode", pattern(compile("#[\\da-f]{3,8}", CASE_INSENSITIVE))),
        token("entity", pattern(compile("\\\\[\\da-f]{1,8}", CASE_INSENSITIVE))),
        token("number", pattern(compile("[\\d%.]+")))
      );
    }
    return null;
  }
}
