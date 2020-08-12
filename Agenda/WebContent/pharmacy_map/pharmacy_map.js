//현재 위치 정보를 허용했을 때
if (navigator.geolocation) {

	// GeoLocation을 이용해서 접속 위치를 얻기
	navigator.geolocation.getCurrentPosition(function getLocation(position) {

				var latitude = position.coords.latitude; // 위도
				var longitude = position.coords.longitude; // 경도

				var mapContainer = document.getElementById('map'), // 맵 컨테이너

				mapOption = {
					center : new kakao.maps.LatLng(latitude, longitude), // 지도의 중심좌표, 위에서 얻은 현재 위치의 위도와 경도가 입력됨
					level : 4 // 지도의 확대 레벨
				};

				// 지도를 생성
				var map = new kakao.maps.Map(mapContainer, mapOption);
				
				// 마우스 휠과 모바일 터치를 이용한 지도 확대, 축소를 막는다
				map.setZoomable(false);

				// 지도에 확대 축소 컨트롤을 생성한다
				var zoomControl = new kakao.maps.ZoomControl();

				// 지도의 우측에 확대 축소 컨트롤을 추가한다
				map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
				
				
				// 현재 위치 재 설정 시작--------------
				// 버튼을 클릭하면 아래 배열의 좌표들이 모두 보이게 지도 범위를 재설정합니다
				var points = new kakao.maps.LatLng(latitude, longitude)

				// 지도를 재설정할 범위정보를 가지고 있을 LatLngBounds 객체를 생성합니다
				var bounds = new kakao.maps.LatLngBounds();

				var marker = new kakao.maps.Marker({
					position : points
				});

				marker.setMap(map);
				bounds.extend(points);

				var current = document.createElement('button');
				var currentClick = document.createTextNode('현재위치 재설정');
				var mapHeader = document.getElementById('map_header');
				current.appendChild(currentClick);
				mapHeader.appendChild(current);
				
				// 현재위치 재설정 버튼을 클릭했을 때 seBounds() 호출
				current.addEventListener('click', function() {
					setBounds();
				})

				function setBounds() {
					// LatLngBounds 객체에 추가된 좌표들을 기준으로 지도의 범위를 재설정
					map.setBounds(bounds);
				}
				// 현재 위치 재 설정 끝 --------------


				// 장소 검색 객체를 생성합니다
				var ps = new kakao.maps.services.Places(map);

				// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
				var infowindow = new kakao.maps.InfoWindow({
					zIndex : 1
				});
				
				// 마커를 클릭했을 때 해당 장소의 상세정보를 보여줄 커스텀오버레이
				var placeOverlay = new kakao.maps.CustomOverlay({
					zIndex : 1
					}), 
					contentNode = document.createElement('div'), // 커스텀 오버레이의 컨텐츠 엘리먼트
					markers = [], // 마커를 담을 배열입니다
					currCategory = ''; // 현재 선택된 카테고리를 가지고 있을 변수입니다

				// 약국 검색
				ps.categorySearch('PM9', placesSearchCB, {
					useMapBounds : true
				})

				// 지도에 idle 이벤트를 등록합니다
				kakao.maps.event.addListener(map, 'idle', searchPlaces);

				// 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
				contentNode.className = 'placeinfo_wrap';

				// 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
				// 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap
				// 메소드를 등록합니다
				addEventHandle(contentNode, 'mousedown', kakao.maps.event.preventMap);
				addEventHandle(contentNode, 'touchstart', kakao.maps.event.preventMap);

				// 커스텀 오버레이 컨텐츠를 설정합니다
				placeOverlay.setContent(contentNode);

				// 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
				function addEventHandle(target, type, callback) {
					if (target.addEventListener) {
						target.addEventListener(type, callback);
					} else {
						target.attachEvent('on' + type, callback);
					}
				}

				// 카테고리 검색을 요청하는 함수입니다
				function searchPlaces() {
					ps.categorySearch('PM9', placesSearchCB, {
						useMapBounds : true
					})
					
					// 커스텀 오버레이를 숨깁니다
					placeOverlay.setMap(null);

					// 지도에 표시되고 있는 마커를 제거합니다
					removeMarker();

				}

				// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
				function placesSearchCB(data, status, pagination) {
					if (status === kakao.maps.services.Status.OK) {

						// 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
						displayPlaces(data);

						// 페이지 번호를 표출합니다
						displayPagination(pagination);
						
					// 검색 결과가 없을 떄
					} else if (status === kakao.maps.services.Status.ZERO_RESULT) {
						alert("근처에 약국이 없습니다");

					} else if (status === kakao.maps.services.Status.ERROR) {
						alert("검색결과 오류");
					}
				}

				// 지도에 마커를 표출하는 함수
				function displayPlaces(places) {

					// 몇번째 카테고리가 선택되어 있는지 얻어옵니다
					// 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
					var order = 1;

					var listEl = document.getElementById('placesList'), 
						menuEl = document.getElementById('menu_wrap'), 
						fragment = document.createDocumentFragment(), 
						listStr = '';

					// 검색 결과 목록에 추가된 항목들을 제거합니다
					removeAllChildNods(listEl);

					// 지도에 표시되고 있는 마커를 제거합니다
					removeMarker();

					for (var i = 0; i < places.length; i++) {

						// 마커를 생성하고 지도에 표시합니다
						var marker = addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order), 
							itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다;

						// 마커와 검색결과 항목을 클릭 했을 때
						// 장소정보를 표출하도록 클릭 이벤트를 등록합니다
						(function(marker, place) {
							kakao.maps.event.addListener(marker, 'mouseover', function() {
										displayPlaceInfo(place);
									});

							itemEl.onmouseover = function() {
								displayPlaceInfo(place);
							};
						})(marker, places[i]);

						fragment.appendChild(itemEl);
					}

					// 검색결과 항목들을 검색결과 목록에 추가
					listEl.appendChild(fragment);
					menuEl.scrollTop = 0;

				}

				// 검색결과 항목을 Element로 반환하는 함수입니다
				function getListItem(index, places) {

					var el = document.createElement('li'), 
					itemStr = '<span class="markerbg marker_'
							+ (index + 1)
							+ '"></span>'
							+ '<div class="info">'
							+ '   <h5>' + places.place_name + '</h5>';

					if (places.road_address_name) {
						itemStr += '    <span>' + places.road_address_name
								+ '</span>' + '   <span class="jibun gray">'
								+ places.address_name + '</span>';
					} else {
						itemStr += '    <span>' + places.address_name
								+ '</span>';
					}

					itemStr += '  <span class="tel">' + places.phone
							+ '</span>' + '</div>';

					el.innerHTML = itemStr;
					el.className = 'item';

					return el;
				}

				// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
				function addMarker(position, order) {
					var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png', // 마커
					imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
					imgOptions = {
						spriteSize : new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
						spriteOrigin : new kakao.maps.Point(46, (2 * 36)), // 스프라이트
						offset : new kakao.maps.Point(11, 28) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
					}, 
					markerImage = new kakao.maps.MarkerImage(imageSrc,
							imageSize, imgOptions), marker = new kakao.maps.Marker(
							{
								position : position, // 마커의 위치
								image : markerImage
							});

					marker.setMap(map); // 지도 위에 마커를 표출합니다
					markers.push(marker); // 배열에 생성된 마커를 추가합니다

					return marker;
				}

				// 지도 위에 표시되고 있는 마커를 모두 제거합니다
				function removeMarker() {
					for (var i = 0; i < markers.length; i++) {
						markers[i].setMap(null);
					}
					markers = [];
				}

				// 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
				function displayPlaceInfo(place) {
					var content = '<div class="placeinfo">'
							+ '   <a class="title" href="' + place.place_url
							+ '" target="_blank" title="' + place.place_name
							+ '">' + place.place_name + '</a>';

					if (place.road_address_name) {
						content += '    <span title="'
								+ place.road_address_name + '">'
								+ place.road_address_name + '</span>'
								+ '  <span class="jibun" title="'
								+ place.address_name + '">(지번 : '
								+ place.address_name + ')</span>';
					} else {
						content += '    <span title="' + place.address_name
								+ '">' + place.address_name + '</span>';
					}

					content += '    <span class="tel">' + place.phone
							+ '</span>' + '</div>'
							+ '<div class="after"></div>';

					contentNode.innerHTML = content;
					placeOverlay.setPosition(new kakao.maps.LatLng(place.y,
							place.x));
					placeOverlay.setMap(map);
				}

				// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
				function displayPagination(pagination) {
					var paginationEl = document.getElementById('pagination'), 
						fragment = document.createDocumentFragment(), i;

					// 기존에 추가된 페이지번호를 삭제합니다
					while (paginationEl.hasChildNodes()) {
						paginationEl.removeChild(paginationEl.lastChild);
					}

					for (i = 1; i <= pagination.last; i++) {
						var el = document.createElement('a');
						el.href = "#";
						el.innerHTML = i;

						if (i === pagination.current) {
							el.className = 'on';
						} else {
							el.onclick = (function(i) {
								return function() {
									pagination.gotoPage(i);
								}
							})(i);
						}

						fragment.appendChild(el);
					}
					paginationEl.appendChild(fragment);
				}

				// 검색결과 목록의 자식 Element를 제거하는 함수입니다
				function removeAllChildNods(el) {
					while (el.hasChildNodes()) {
						el.removeChild(el.lastChild);
					}
				}

			});

} else { //현재 위치 서비스를 지원하지 않을 때 
	console.log("현재 위치 서비스를 지원하지 않는 브라우저입니다.");
}
