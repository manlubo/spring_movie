## 2025-07-30

- `UploadController` 작성
  - Ajax 기반 이미지 업로드 처리
  - 업로드 경로는 `application.yml`에서 설정된 경로 사용
  - 업로드 시 MIME 타입이 image로 시작하지 않으면 업로드 거부
  - `UUID + 원본파일명` 형태로 파일 저장하여 중복 방지
  - 날짜(`yyyy/MM/dd`) 기반 폴더 자동 생성
  - 업로드 성공 시 JSON 형태로 응답 (`origin`, `size`, `uuid`, `path`)
- `/uploadAjax` : 업로드 처리