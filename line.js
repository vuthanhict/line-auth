/**
 * RANDOM STRING GENERATOR
 *
 * Info:      http://stackoverflow.com/a/27872144/383904
 * Use:       randomString(length [,"A"] [,"N"] );
 * Default:   return a random alpha-numeric string
 * Arguments: If you use the optional "A", "N" flags:
 *            "A" (Alpha flag)   return random a-Z string
 *            "N" (Numeric flag) return random 0-9 string
 */
function randomString(len, an){
    an = an&&an.toLowerCase();
    var str="", i=0, min=an=="a"?10:0, max=an=="n"?10:62;
    for(;i++<len;){
      var r = Math.random()*(max-min)+min <<0;
      str += String.fromCharCode(r+=r>9?r<36?55:61:48);
    }
    return str;
}

/**
* state = randomString(32, "an");
* nonce = randomString(32, "an");
* Authorizationエンドポイント
*/
function getLineAuthUrl(state, nonce) {
    var url = "https://access.line.me/oauth2/v2.1/authorize?";
    url = url + "response_type=code";
    url = url + "&client_id=1621437681";
    url = url + "&redirect_uri=https%3A%2F%2Flocalhost%3A8080%2Fline-callback"; // https%3A%2F%2Flocalhost%3A8080%2Fline-callback
    url = url + "&state=" + state;
    url = url + "&scope=openid%20profile";
    url = url + "&nonce=" + nonce;
    return url;
}
