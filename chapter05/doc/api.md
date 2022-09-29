## Hotels > Rooms 
### GET Rooms
- 호텔 방의 정보를 조회한다.

#### REST-API 요청
GET /hotels/{hotelId}/rooms/{roomNumber}?fromDate={yyyyMMdd}&toDate={yyyyMMdd}
- hotelId : (필수) Long 타입이며, 호텔의 유니크 아이디 값. 
- roomNumber : (필수) String 타입이며, 호텔 리소스에 포함된 룸들 중 유니크한 아이디 값
- fromDate, toDate : (선택) String 타입이며, yyyyMMdd 형식의 예약일.

#### REST-API 응답
- [HEADER] X-CREATED-AT : yyyy-MM-dd'T'HH:mm:ssXXX 

{
    “id” : “1201928183”,
    “roomNumber” : “West-Wing-3928”,
    “numberOfBeds” : 2,
    "roomType" : "deluxe",
    “originalPrice” : “150.00”,
    “reservations” : [
	    { 
	        "id" : "129171201928183",
	        “reservedDate” : "{yyyyMMdd}"
	    },{ 
            "id" : "129171201928183",
        	“reservedDate” : "{yyyyMMdd}"
        }
    ]
}

### Create Rooms
- 호텔 방의 정보를 서버에 저장한다.

#### REST-API 요청
POST /hotels/{hotelId}/rooms

{
    “roomNumber” : “West-Wing-3928”,
    "roomType" : "deluxe",
    “originalPrice” : “150.00”
}

#### REST-API 응답 
{
    “id” : “1201928183” //생성된 호텔방의 유니크 아이디를 응답한다.
}




### Delete Room 

#### REST-API 요청
DELETE /hotels/{hotelId}/rooms/{roomNumber}
- hotelId : (필수) Long 타입이며, 호텔의 유니크 아이디 값. 
- roomNumber : (필수) String 타입이며, 호텔 리소스에 포함된 룸들 중 유니크한 아이디 값

#### REST-API 응답
{
    “isSuccess” : true,
    “message” : “success”
}


### GET Reservations 

#### REST-API 요청
GET /hotels/{hotelId}/rooms/{roomNumber}/reservations
- hotelId : (필수) Long 타입이며, 호텔의 유니크 아이디 값. 
- roomNumber : (필수) String 타입이며, 호텔 리소스에 포함된 룸들 중 유니크한 아이디 값
- page : 페이지 번호이며 0부터 시작
- size : 페이지당 포함할 예약 정보의 개수
- sort : 소팅 정보. 정렬 프로퍼티 이름과 뱡향키워드를 같이 사용하며 콤마(,)로 구분한다.
    - 방향 키워드로는 순차 정렬을 의미하는 ASC, 역순 정렬을 의미하는 DESC가 있다.  
    - e.g. reservationId,asc
    - e.g. reservationDate,desc
