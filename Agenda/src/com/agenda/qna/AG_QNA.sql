--글번호, 제목, 내용, 작성일, 조회수, 삭제여부, ID

CREATE SEQUENCE QNA_NO_SEQ;
DROP SEQUENCE QNA_NO_SEQ;

SELECT QNA_NO, QNA_TITLE, QNA_CONTENT, QNA_REGDATE, QNA_HIT, QNA_DELFLAG, MEMBER_ID
FROM AG_QNA
ORDER BY QNA_NO DESC;

DELETE FROM AG_QNA
WHERE QNA_NO > 1;

SELECT QNA_HIT
FROM AG_QNA
ORDER BY QNA_NO DESC;


SELECT * 
FROM AG_QNA Q
LEFT JOIN AG_COMMENT C ON Q.MEMBER_ID = C.MEMBER_ID
ORDER BY QNA_NO;

DELETE FROM AG_QNA
WHERE QNA_NO > 1


