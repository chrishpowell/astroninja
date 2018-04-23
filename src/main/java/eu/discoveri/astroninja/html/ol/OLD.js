/* 
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */
import 'ol/ol.css';
import GeoJSON from 'ol/format/geojson';
import Map from 'ol/map';
import View from 'ol/view';
import VectorLayer from 'ol/layer/vector';
import VectorSource from 'ol/source/vector';
import sync from 'ol-hashed';
import DragDrop from 'ol/interaction/draganddrop';
//import TileLayer from 'ol/layer/tile';
//import XYZSource from 'ol/source/xyz';
//import proj from 'ol/proj';

const map = new Map({
    target: 'map-container',
//    layers: [
//  2      new VectorLayer({
//            source: new VectorSource({
//                format: new GeoJSON(),
//                url: './data/countries.json'
//            })
//        })
//  1  new TileLayer({
//      source: new XYZSource({
//        url: 'http://tile.stamen.com/terrain/{z}/{x}/{y}.jpg'
//      })
//    })
//    ],
    view: new View({
        center: [0, 0],
        zoom: 2
    })
});
sync(map);
//navigator.geolocation.getCurrentPosition(function (pos) {
//    const coords = proj.fromLonLat([pos.coords.longitude, pos.coords.latitude]);
//    map.getView().animate({center: coords, zoom: 10});
//});
const source = new VectorSource();
const layer = new VectorLayer({
    source: source
});
map.addLayer(layer);
map.addInteraction(new DragDrop({
  source: source,
  formatConstructors: [GeoJSON]
}));
