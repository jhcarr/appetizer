/* 
   Other charts may be added here in the same way:
   <function defining chart space>
   <rendering function>
*/

function checkinsDemonstration (location, dimension, group) {

    console.log("Begin checkinsDemonstration");

//    var dimensionGroup = dimension.group().reduceCount().orderNatural();

    return dc.barChart(location)
        .height(450)
        .width(900)
        .dimension(dimension)
        .group(group)
        .x(d3.time.scale().domain([new Date(2013, 6, 1), new Date(2013, 6, 20)]))
        .margins({top: 50, bottom: 50, right: 50, left: 50})
        .transitionDuration(0)
        .xAxis().tickFormat(d3.time.format("%Y-%m-%d"));
}

function drawCheckins() {

console.log(queryData);

var filtered = crossfilter(queryData);

var day = filtered.dimension( function (f) { return f.date;} );

var days = day.group(d3.time.day).reduceCount();

checkinsDemonstration( '#checkins_by_date', day, days );

dc.renderAll();

}
