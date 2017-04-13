(function (factory) {
	if (typeof define === "function" && define.amd) {
		define(["inputmask.dependencyLib", "inputmask"], factory);
	} else if (typeof exports === "object") {
		module.exports = factory(require("./inputmask.dependencyLib"), require("./inputmask"));
	} else {
		factory(window.dependencyLib || jQuery, window.Inputmask);
	}
}
(function ($, Inputmask) {	
	Inputmask.extendAliases({

		"string": {
			mask: "S{1,64}",
			greedy: false,
			definitions: {
				"S": {
					validator: "[0-9A-Za-z!#$%&'*+/=?^_`{|}~\-]",
					cardinality: 1,
				},
			},
			inputmode: "string",
		}
     });
	return Inputmask;
}));
