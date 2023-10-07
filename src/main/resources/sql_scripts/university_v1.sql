--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-10-07 12:55:56

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 214 (class 1259 OID 60826)
-- Name: admins; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.admins (
    user_id bigint NOT NULL
);


ALTER TABLE public.admins OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 60832)
-- Name: classrooms; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.classrooms (
    classroom_id bigint NOT NULL,
    description character varying(255),
    number character varying(255)
);


ALTER TABLE public.classrooms OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 60831)
-- Name: classrooms_classroom_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.classrooms_classroom_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.classrooms_classroom_id_seq OWNER TO postgres;

--
-- TOC entry 3412 (class 0 OID 0)
-- Dependencies: 215
-- Name: classrooms_classroom_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.classrooms_classroom_id_seq OWNED BY public.classrooms.classroom_id;


--
-- TOC entry 217 (class 1259 OID 60840)
-- Name: classrooms_courses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.classrooms_courses (
    classroom_id bigint NOT NULL,
    course_id bigint NOT NULL
);


ALTER TABLE public.classrooms_courses OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 60844)
-- Name: courses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.courses (
    course_id bigint NOT NULL,
    course_description character varying(255),
    course_name character varying(255)
);


ALTER TABLE public.courses OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 60843)
-- Name: courses_course_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.courses_course_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.courses_course_id_seq OWNER TO postgres;

--
-- TOC entry 3413 (class 0 OID 0)
-- Dependencies: 218
-- Name: courses_course_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.courses_course_id_seq OWNED BY public.courses.course_id;


--
-- TOC entry 221 (class 1259 OID 60853)
-- Name: groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.groups (
    group_id bigint NOT NULL,
    group_name character varying(255)
);


ALTER TABLE public.groups OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 60859)
-- Name: groups_courses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.groups_courses (
    group_id bigint NOT NULL,
    course_id bigint NOT NULL
);


ALTER TABLE public.groups_courses OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 60852)
-- Name: groups_group_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.groups_group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.groups_group_id_seq OWNER TO postgres;

--
-- TOC entry 3414 (class 0 OID 0)
-- Dependencies: 220
-- Name: groups_group_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.groups_group_id_seq OWNED BY public.groups.group_id;


--
-- TOC entry 224 (class 1259 OID 60863)
-- Name: lessons; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lessons (
    lesson_id bigint NOT NULL,
    day smallint,
    lesson_number smallint,
    classroom_id bigint,
    course_id bigint,
    group_id bigint,
    teacher_id bigint,
    CONSTRAINT lessons_day_check CHECK (((day >= 0) AND (day <= 4))),
    CONSTRAINT lessons_lesson_number_check CHECK (((lesson_number >= 0) AND (lesson_number <= 3)))
);


ALTER TABLE public.lessons OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 60862)
-- Name: lessons_lesson_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.lessons_lesson_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lessons_lesson_id_seq OWNER TO postgres;

--
-- TOC entry 3415 (class 0 OID 0)
-- Dependencies: 223
-- Name: lessons_lesson_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.lessons_lesson_id_seq OWNED BY public.lessons.lesson_id;


--
-- TOC entry 225 (class 1259 OID 60871)
-- Name: students; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.students (
    user_id bigint NOT NULL,
    group_id bigint
);


ALTER TABLE public.students OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 60876)
-- Name: teachers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.teachers (
    user_id bigint NOT NULL,
    teach_course_id bigint
);


ALTER TABLE public.teachers OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 60882)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id bigint NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    role smallint,
    CONSTRAINT users_role_check CHECK (((role >= 0) AND (role <= 2)))
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 60881)
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_user_id_seq OWNER TO postgres;

--
-- TOC entry 3416 (class 0 OID 0)
-- Dependencies: 227
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- TOC entry 3213 (class 2604 OID 60835)
-- Name: classrooms classroom_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.classrooms ALTER COLUMN classroom_id SET DEFAULT nextval('public.classrooms_classroom_id_seq'::regclass);


--
-- TOC entry 3214 (class 2604 OID 60847)
-- Name: courses course_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses ALTER COLUMN course_id SET DEFAULT nextval('public.courses_course_id_seq'::regclass);


--
-- TOC entry 3215 (class 2604 OID 60856)
-- Name: groups group_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups ALTER COLUMN group_id SET DEFAULT nextval('public.groups_group_id_seq'::regclass);


--
-- TOC entry 3216 (class 2604 OID 60866)
-- Name: lessons lesson_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons ALTER COLUMN lesson_id SET DEFAULT nextval('public.lessons_lesson_id_seq'::regclass);


--
-- TOC entry 3217 (class 2604 OID 60885)
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- TOC entry 3392 (class 0 OID 60826)
-- Dependencies: 214
-- Data for Name: admins; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.admins (user_id) FROM stdin;
\.


--
-- TOC entry 3394 (class 0 OID 60832)
-- Dependencies: 216
-- Data for Name: classrooms; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.classrooms (classroom_id, description, number) FROM stdin;
\.


--
-- TOC entry 3395 (class 0 OID 60840)
-- Dependencies: 217
-- Data for Name: classrooms_courses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.classrooms_courses (classroom_id, course_id) FROM stdin;
\.


--
-- TOC entry 3397 (class 0 OID 60844)
-- Dependencies: 219
-- Data for Name: courses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.courses (course_id, course_description, course_name) FROM stdin;
\.


--
-- TOC entry 3399 (class 0 OID 60853)
-- Dependencies: 221
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.groups (group_id, group_name) FROM stdin;
\.


--
-- TOC entry 3400 (class 0 OID 60859)
-- Dependencies: 222
-- Data for Name: groups_courses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.groups_courses (group_id, course_id) FROM stdin;
\.


--
-- TOC entry 3402 (class 0 OID 60863)
-- Dependencies: 224
-- Data for Name: lessons; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.lessons (lesson_id, day, lesson_number, classroom_id, course_id, group_id, teacher_id) FROM stdin;
\.


--
-- TOC entry 3403 (class 0 OID 60871)
-- Dependencies: 225
-- Data for Name: students; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.students (user_id, group_id) FROM stdin;
\.


--
-- TOC entry 3404 (class 0 OID 60876)
-- Dependencies: 226
-- Data for Name: teachers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.teachers (user_id, teach_course_id) FROM stdin;
\.


--
-- TOC entry 3406 (class 0 OID 60882)
-- Dependencies: 228
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, first_name, last_name, role) FROM stdin;
\.


--
-- TOC entry 3417 (class 0 OID 0)
-- Dependencies: 215
-- Name: classrooms_classroom_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.classrooms_classroom_id_seq', 1, false);


--
-- TOC entry 3418 (class 0 OID 0)
-- Dependencies: 218
-- Name: courses_course_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.courses_course_id_seq', 1, false);


--
-- TOC entry 3419 (class 0 OID 0)
-- Dependencies: 220
-- Name: groups_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.groups_group_id_seq', 1, false);


--
-- TOC entry 3420 (class 0 OID 0)
-- Dependencies: 223
-- Name: lessons_lesson_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.lessons_lesson_id_seq', 1, false);


--
-- TOC entry 3421 (class 0 OID 0)
-- Dependencies: 227
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 1, false);


--
-- TOC entry 3222 (class 2606 OID 60830)
-- Name: admins admins_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admins
    ADD CONSTRAINT admins_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3224 (class 2606 OID 60839)
-- Name: classrooms classrooms_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.classrooms
    ADD CONSTRAINT classrooms_pkey PRIMARY KEY (classroom_id);


--
-- TOC entry 3226 (class 2606 OID 60851)
-- Name: courses courses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_pkey PRIMARY KEY (course_id);


--
-- TOC entry 3228 (class 2606 OID 60858)
-- Name: groups groups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (group_id);


--
-- TOC entry 3230 (class 2606 OID 60870)
-- Name: lessons lessons_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT lessons_pkey PRIMARY KEY (lesson_id);


--
-- TOC entry 3232 (class 2606 OID 60875)
-- Name: students students_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.students
    ADD CONSTRAINT students_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3234 (class 2606 OID 60880)
-- Name: teachers teachers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teachers
    ADD CONSTRAINT teachers_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3236 (class 2606 OID 60889)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3242 (class 2606 OID 60920)
-- Name: lessons fk17ucc7gjfjddsyi0gvstkqeat; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT fk17ucc7gjfjddsyi0gvstkqeat FOREIGN KEY (course_id) REFERENCES public.courses(course_id);


--
-- TOC entry 3240 (class 2606 OID 60905)
-- Name: groups_courses fk68xnkbo7urhqormcaa50dcxuw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups_courses
    ADD CONSTRAINT fk68xnkbo7urhqormcaa50dcxuw FOREIGN KEY (course_id) REFERENCES public.courses(course_id);


--
-- TOC entry 3241 (class 2606 OID 60910)
-- Name: groups_courses fkb5nvrwxye8n0ct77q8s3war1x; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups_courses
    ADD CONSTRAINT fkb5nvrwxye8n0ct77q8s3war1x FOREIGN KEY (group_id) REFERENCES public.groups(group_id);


--
-- TOC entry 3248 (class 2606 OID 60950)
-- Name: teachers fkb8dct7w2j1vl1r2bpstw5isc0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teachers
    ADD CONSTRAINT fkb8dct7w2j1vl1r2bpstw5isc0 FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 3243 (class 2606 OID 60915)
-- Name: lessons fkbffxqtymudjwdb39m7dnjn4ey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT fkbffxqtymudjwdb39m7dnjn4ey FOREIGN KEY (classroom_id) REFERENCES public.classrooms(classroom_id);


--
-- TOC entry 3244 (class 2606 OID 60930)
-- Name: lessons fkbr76cuebuufbbltujpfq04tto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT fkbr76cuebuufbbltujpfq04tto FOREIGN KEY (teacher_id) REFERENCES public.teachers(user_id);


--
-- TOC entry 3246 (class 2606 OID 60940)
-- Name: students fkdt1cjx5ve5bdabmuuf3ibrwaq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.students
    ADD CONSTRAINT fkdt1cjx5ve5bdabmuuf3ibrwaq FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 3237 (class 2606 OID 60890)
-- Name: admins fkgc8dtql9mkq268detxiox7fpm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admins
    ADD CONSTRAINT fkgc8dtql9mkq268detxiox7fpm FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 3249 (class 2606 OID 60945)
-- Name: teachers fkk805y9f18ppaml2ng1c1fhe7n; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teachers
    ADD CONSTRAINT fkk805y9f18ppaml2ng1c1fhe7n FOREIGN KEY (teach_course_id) REFERENCES public.courses(course_id);


--
-- TOC entry 3238 (class 2606 OID 60900)
-- Name: classrooms_courses fkk8rbwp31oatef2ohykhr6b6hq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.classrooms_courses
    ADD CONSTRAINT fkk8rbwp31oatef2ohykhr6b6hq FOREIGN KEY (classroom_id) REFERENCES public.classrooms(classroom_id);


--
-- TOC entry 3247 (class 2606 OID 60935)
-- Name: students fkmsev1nou0j86spuk5jrv19mss; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.students
    ADD CONSTRAINT fkmsev1nou0j86spuk5jrv19mss FOREIGN KEY (group_id) REFERENCES public.groups(group_id);


--
-- TOC entry 3239 (class 2606 OID 60895)
-- Name: classrooms_courses fkr33hf7ya3mj3l45uk13st6ufk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.classrooms_courses
    ADD CONSTRAINT fkr33hf7ya3mj3l45uk13st6ufk FOREIGN KEY (course_id) REFERENCES public.courses(course_id);


--
-- TOC entry 3245 (class 2606 OID 60925)
-- Name: lessons fktdolsaotaqlwxbxwaxt00kimk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT fktdolsaotaqlwxbxwaxt00kimk FOREIGN KEY (group_id) REFERENCES public.groups(group_id);


-- Completed on 2023-10-07 12:55:56

--
-- PostgreSQL database dump complete
--

