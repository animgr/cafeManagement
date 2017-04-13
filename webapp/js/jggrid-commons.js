//jqGrid static sets
$.jgrid.defaults.guiStyle="bootstrap";
$.jgrid.defaults.locale ="fa";
$.jgrid.defaults.iconSet = "fontAwesome";
$.jgrid.defaults.autowidth = true;
$.jgrid.defaults.autoresizeOnLoad = true;  
$.jgrid.defaults.autoResizing = { compact: true };
$.jgrid.defaults.viewrecords = true;        
$.jgrid.defaults.rowNum = 10;
$.jgrid.formatter.integer={thousandsSeparator: ","};


var getUniqueNames = function(columnName, mydata) {
    var texts = $.map(mydata, function(item) {
        return item[columnName];
    }),        
        uniqueTexts = [],
        textsLength = texts.length,
        text, textsMap = {},
        i;
    
    for (i = 0; i < textsLength; i++) {
        text = texts[i];
        if (text !== undefined && textsMap[text] === undefined) {
            // to test whether the texts is unique we place it in the map.
            textsMap[text] = true;
            uniqueTexts.push(text);
        }
    }       
    return uniqueTexts;
},

buildSearchSelect = function(uniqueNames) {
    var values = ":"+ global_labels.all;
    $.each(uniqueNames, function() {
        values +=  ";" + this + ":" + this;
    });
    return values;
},
setSearchSelect = function(columnName, data) {    	
    $(this).jqGrid("setColProp", columnName, {
        stype: "select",
        align: "center",
        searchoptions: {
            value: buildSearchSelect(getUniqueNames.call(this, columnName, data)),
            sopt: ["eq", "ne"]
        }
    });
};
