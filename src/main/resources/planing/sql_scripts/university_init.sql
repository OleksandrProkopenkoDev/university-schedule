--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-10-18 11:49:25

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
-- TOC entry 215 (class 1259 OID 61417)
-- Name: admins; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.admins (
    user_id bigint NOT NULL
);


ALTER TABLE public.admins OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 61423)
-- Name: classrooms; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.classrooms (
    classroom_id bigint NOT NULL,
    description character varying(255),
    number character varying(255)
);


ALTER TABLE public.classrooms OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 61422)
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
-- TOC entry 3421 (class 0 OID 0)
-- Dependencies: 216
-- Name: classrooms_classroom_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.classrooms_classroom_id_seq OWNED BY public.classrooms.classroom_id;


--
-- TOC entry 218 (class 1259 OID 61431)
-- Name: classrooms_courses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.classrooms_courses (
    classroom_id bigint NOT NULL,
    course_id bigint NOT NULL
);


ALTER TABLE public.classrooms_courses OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 61435)
-- Name: courses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.courses (
    course_id bigint NOT NULL,
    course_description character varying(255),
    course_name character varying(255)
);


ALTER TABLE public.courses OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 61434)
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
-- TOC entry 3422 (class 0 OID 0)
-- Dependencies: 219
-- Name: courses_course_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.courses_course_id_seq OWNED BY public.courses.course_id;


--
-- TOC entry 214 (class 1259 OID 61408)
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 61444)
-- Name: groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.groups (
    group_id bigint NOT NULL,
    group_name character varying(255)
);


ALTER TABLE public.groups OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 61450)
-- Name: groups_courses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.groups_courses (
    group_id bigint NOT NULL,
    course_id bigint NOT NULL
);


ALTER TABLE public.groups_courses OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 61443)
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
-- TOC entry 3423 (class 0 OID 0)
-- Dependencies: 221
-- Name: groups_group_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.groups_group_id_seq OWNED BY public.groups.group_id;


--
-- TOC entry 225 (class 1259 OID 61454)
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
-- TOC entry 224 (class 1259 OID 61453)
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
-- TOC entry 3424 (class 0 OID 0)
-- Dependencies: 224
-- Name: lessons_lesson_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.lessons_lesson_id_seq OWNED BY public.lessons.lesson_id;


--
-- TOC entry 226 (class 1259 OID 61462)
-- Name: students; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.students (
    user_id bigint NOT NULL,
    group_id bigint
);


ALTER TABLE public.students OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 61467)
-- Name: teachers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.teachers (
    user_id bigint NOT NULL,
    teach_course_id bigint
);


ALTER TABLE public.teachers OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 61473)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id bigint NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    password character varying(255),
    role smallint,
    username character varying(255),
    CONSTRAINT users_role_check CHECK (((role >= 0) AND (role <= 2)))
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 61472)
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
-- TOC entry 3425 (class 0 OID 0)
-- Dependencies: 228
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- TOC entry 3218 (class 2604 OID 61426)
-- Name: classrooms classroom_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.classrooms ALTER COLUMN classroom_id SET DEFAULT nextval('public.classrooms_classroom_id_seq'::regclass);


--
-- TOC entry 3219 (class 2604 OID 61438)
-- Name: courses course_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses ALTER COLUMN course_id SET DEFAULT nextval('public.courses_course_id_seq'::regclass);


--
-- TOC entry 3220 (class 2604 OID 61447)
-- Name: groups group_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups ALTER COLUMN group_id SET DEFAULT nextval('public.groups_group_id_seq'::regclass);


--
-- TOC entry 3221 (class 2604 OID 61457)
-- Name: lessons lesson_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons ALTER COLUMN lesson_id SET DEFAULT nextval('public.lessons_lesson_id_seq'::regclass);


--
-- TOC entry 3222 (class 2604 OID 61476)
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- TOC entry 3401 (class 0 OID 61417)
-- Dependencies: 215
-- Data for Name: admins; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.admins (user_id) FROM stdin;
1
2
3
\.


--
-- TOC entry 3403 (class 0 OID 61423)
-- Dependencies: 217
-- Data for Name: classrooms; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.classrooms (classroom_id, description, number) FROM stdin;
1	Chemistry Lab: Chemical experiments and laboratory research. Equipment: Lab stations, chemicals, safety apparatus. Capacity: 20 seats. Type: Chemistry laboratory.	A3-323
2	Computer Science Lab: Practical programming sessions and software development. Equipment: Computers, coding boards, comfortable seating. Capacity: 25 seats. Type: Computer lab.	A1-214
3	Literature Classroom: Fosters literary analysis and group readings. Equipment: Library corner, projector, comfortable seating. Capacity: 35 seats. Type: Reading and discussion room.	A2-108
4	Environmental Science Lab: Equipped for hands-on experiments and data analysis. Equipment: Microscopes, specimens, lab stations. Capacity: 20 seats. Type: Experimental laboratory.	C2-101
5	Political Economy Classroom: Analyzing the intersection of politics and economics. Equipment: Whiteboard, projector, round-table seating. Capacity: 35 seats. Type: Round-table discussion room.	A3-111
6	Business Management Classroom: Strategy discussions and case studies. Equipment: Presentation board, seminar tables, comfortable seating. Capacity: 30 seats. Type: Seminar room.	B2-326
7	Human Biology Lab: Hands-on anatomy experiments and dissections. Equipment: Lab stations, specimens, microscopes. Capacity: 20 seats. Type: Biology lab.	D3-326
8	Art History Lecture Hall: Artistic analysis and visual presentations. Equipment: Projector, art displays, tiered seating. Capacity: 100 seats. Type: Lecture hall.	A1-206
9	Physics Lecture Hall: Large-scale physics lectures and demonstrations. Equipment: Large projector, interactive boards, tiered seating. Capacity: 150 seats. Type: Lecture hall.	A1-313
10	World History Classroom: Ideal for historical lectures and group discussions. Equipment: Maps, projector, podium. Capacity: 40 seats. Type: Lecture and discussion room.	A3-311
11	Spanish Language Lab: Language immersion and communication practice. Equipment: Language software, audio stations, comfortable seating. Capacity: 25 seats. Type: Language lab.	B3-123
12	Spanish Language Lab: Language immersion and communication practice. Equipment: Language software, audio stations, comfortable seating. Capacity: 25 seats. Type: Language lab.	D2-225
13	Digital Marketing Classroom: Interactive discussions on digital strategies. Equipment: Smartboards, computers, comfortable seating. Capacity: 30 seats. Type: Discussion and workshop room.	B2-324
14	Business Management Classroom: Strategy discussions and case studies. Equipment: Presentation board, seminar tables, comfortable seating. Capacity: 30 seats. Type: Seminar room.	B2-102
15	Digital Marketing Classroom: Interactive discussions on digital strategies. Equipment: Smartboards, computers, comfortable seating. Capacity: 30 seats. Type: Discussion and workshop room.	B2-325
16	Political Science Classroom: Dynamic discussions on global political issues. Equipment: Projector, podium, round-table seating. Capacity: 40 seats. Type: Round-table discussion room.	B3-101
17	Film Studies Classroom: Cinematic analysis and film screenings. Equipment: Projector, sound system, tiered seating. Capacity: 40 seats. Type: Screening room.	B2-325
18	World History Classroom: Ideal for historical lectures and group discussions. Equipment: Maps, projector, podium. Capacity: 40 seats. Type: Lecture and discussion room.	B2-202
19	Digital Marketing Classroom: Interactive discussions on digital strategies. Equipment: Smartboards, computers, comfortable seating. Capacity: 30 seats. Type: Discussion and workshop room.	C3-110
20	Statistics Classroom: Analytical discussions and statistical modeling. Equipment: Whiteboard, statistical software, tables. Capacity: 30 seats. Type: Discussion and analysis room.	B1-210
21	Film Studies Classroom: Cinematic analysis and film screenings. Equipment: Projector, sound system, tiered seating. Capacity: 40 seats. Type: Screening room.	C3-201
22	Robotics Lab: Hands-on robotics projects and programming. Equipment: Robotic kits, programming stations, collaborative tables. Capacity: 20 seats. Type: Robotics laboratory.	C1-220
23	Robotics Lab: Hands-on robotics projects and programming. Equipment: Robotic kits, programming stations, collaborative tables. Capacity: 20 seats. Type: Robotics laboratory.	B2-212
24	Mathematics Workshop: Problem-solving sessions and mathematical discussions. Equipment: Whiteboards, mathematical models, seminar tables. Capacity: 30 seats. Type: Workshop room.	B1-207
25	Music Appreciation Room: Musical analysis and appreciation sessions. Equipment: Sound system, musical instruments, comfortable seating. Capacity: 30 seats. Type: Music appreciation room.	D3-300
26	Music Appreciation Room: Musical analysis and appreciation sessions. Equipment: Sound system, musical instruments, comfortable seating. Capacity: 30 seats. Type: Music appreciation room.	A2-218
27	Political Science Classroom: Dynamic discussions on global political issues. Equipment: Projector, podium, round-table seating. Capacity: 40 seats. Type: Round-table discussion room.	C2-313
28	World History Classroom: Ideal for historical lectures and group discussions. Equipment: Maps, projector, podium. Capacity: 40 seats. Type: Lecture and discussion room.	D3-213
29	Ethics Classroom: Ethical debates and case studies. Equipment: Discussion boards, comfortable seating. Capacity: 25 seats. Type: Discussion room.	A3-318
30	Mathematics Workshop: Problem-solving sessions and mathematical discussions. Equipment: Whiteboards, mathematical models, seminar tables. Capacity: 30 seats. Type: Workshop room.	B1-121
31	Film Studies Classroom: Cinematic analysis and film screenings. Equipment: Projector, sound system, tiered seating. Capacity: 40 seats. Type: Screening room.	B1-200
32	Geography Classroom: Spatial analysis and geographic discussions. Equipment: Maps, globe, whiteboard, comfortable seating. Capacity: 35 seats. Type: Discussion and analysis room.	D2-111
33	Psychology Classroom: Designed for interactive discussions and psychological experiments. Equipment: Whiteboard, projector, comfortable seating. Capacity: 30 seats. Type: Interactive discussion and experimental classroom.	D1-213
34	Mathematics Workshop: Problem-solving sessions and mathematical discussions. Equipment: Whiteboards, mathematical models, seminar tables. Capacity: 30 seats. Type: Workshop room.	B1-121
35	Spanish Language Lab: Language immersion and communication practice. Equipment: Language software, audio stations, comfortable seating. Capacity: 25 seats. Type: Language lab.	C2-217
\.


--
-- TOC entry 3404 (class 0 OID 61431)
-- Dependencies: 218
-- Data for Name: classrooms_courses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.classrooms_courses (classroom_id, course_id) FROM stdin;
1	12
2	4
2	7
2	8
3	7
3	2
3	9
4	9
4	12
5	6
5	7
5	14
6	8
6	3
6	6
7	10
7	9
8	13
8	1
9	11
9	13
9	14
10	5
11	5
11	3
12	5
12	14
13	8
13	13
14	13
14	3
15	14
15	4
15	10
16	10
16	9
17	6
17	14
17	15
18	1
19	7
19	13
19	9
20	10
20	6
20	9
21	2
22	6
22	10
22	9
23	2
24	2
24	7
25	10
26	14
26	13
26	6
27	7
27	12
28	15
28	1
29	2
29	15
29	7
30	12
31	10
32	10
32	8
33	5
34	3
35	2
\.


--
-- TOC entry 3406 (class 0 OID 61435)
-- Dependencies: 220
-- Data for Name: courses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.courses (course_id, course_description, course_name) FROM stdin;
1	Discover the basics of psychology, covering topics such as behavior, cognition, and psychological disorders.	Introduction to Psychology
2	Develop writing skills and explore various genres in a creative workshop setting.	Creative Writing Workshop
3	Learn to build dynamic web applications using Java Servlets and JavaServer Pages (JSP).	Web Application Development with Java Servlets and JSP
4	Learn the basics of programming, algorithms, and computer systems.	Introduction to Computer Science
5	Analyze and appreciate the art and cultural impact of films from various genres.	Introduction to Film Studies
6	Explore object-oriented concepts like classes, objects, inheritance, and polymorphism in Java.	Object-Oriented Programming with Java
7	Explore the fundamental principles of psychology and human behavior.	Introduction to Psychology
8	Study the structure and dynamics of human societies and social relationships.	Introduction to Sociology
9	Examine environmental issues and learn about sustainable practices to protect our planet.	Environmental Science and Sustainability
10	Develop statistical literacy and analytical skills for informed decision-making.	Statistics for Decision Making
11	Study the fundamental principles of physics, including mechanics, electromagnetism, and thermodynamics.	Physics for Engineers
12	An introduction to the Spring Framework for building Java-based enterprise applications.	Java Spring Framework Fundamentals
13	An introductory course covering basic principles of physics in everyday life.	Physics for Non-Science Majors
14	Learn how to integrate Hibernate for object-relational mapping (ORM) in Java applications.	Java Persistence with Hibernate
15	Investigate the diversity of human cultures and societies.	Cultural Anthropology: Understanding Human Societies
\.


--
-- TOC entry 3400 (class 0 OID 61408)
-- Dependencies: 214
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
\.


--
-- TOC entry 3408 (class 0 OID 61444)
-- Dependencies: 222
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.groups (group_id, group_name) FROM stdin;
1	SU-20
2	LB-57
3	YR-52
4	LP-47
5	AN-23
6	BW-06
7	LU-72
8	JJ-76
9	BK-25
10	JN-78
\.


--
-- TOC entry 3409 (class 0 OID 61450)
-- Dependencies: 223
-- Data for Name: groups_courses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.groups_courses (group_id, course_id) FROM stdin;
1	6
1	7
1	8
1	9
1	10
1	12
1	13
2	2
2	3
2	4
2	5
2	6
2	7
2	8
2	9
3	8
3	9
3	10
3	11
3	13
3	14
4	7
4	8
4	9
4	10
4	11
4	12
4	15
5	5
5	6
5	7
5	8
5	9
5	11
6	9
6	10
6	11
6	12
6	13
6	14
6	15
7	4
7	5
7	6
7	7
7	8
7	9
7	10
8	7
8	8
8	9
8	10
8	11
8	12
8	13
8	14
9	6
9	7
9	9
9	10
9	11
9	15
10	3
10	4
10	6
10	7
10	8
10	9
10	12
10	13
\.


--
-- TOC entry 3411 (class 0 OID 61454)
-- Dependencies: 225
-- Data for Name: lessons; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.lessons (lesson_id, day, lesson_number, classroom_id, course_id, group_id, teacher_id) FROM stdin;
1	0	0	35	2	2	35
2	0	0	5	14	3	47
3	0	0	2	7	5	10
4	0	0	3	9	6	12
5	0	0	11	5	7	8
6	0	0	9	11	8	29
7	0	0	20	10	9	13
8	0	0	14	3	10	6
9	0	1	25	10	1	13
10	0	1	10	5	2	23
11	0	1	13	8	3	26
12	0	1	15	10	4	28
13	0	1	12	5	5	8
14	0	1	5	14	6	32
15	0	1	2	4	7	22
16	0	1	24	7	8	19
17	0	1	26	6	9	9
18	0	1	19	9	10	42
19	0	2	32	8	1	41
20	0	2	22	6	2	9
21	0	2	15	10	3	13
22	0	2	3	7	4	10
23	0	2	20	9	5	12
24	0	2	8	13	6	31
25	0	2	16	9	7	42
26	0	2	7	9	8	27
27	0	2	9	11	9	44
28	0	2	30	12	10	15
29	0	3	20	6	1	39
30	0	3	13	8	2	26
31	0	3	30	12	4	45
32	0	3	9	11	5	14
33	0	3	7	9	7	12
34	0	3	22	10	8	13
35	1	0	26	13	1	31
36	1	0	11	5	2	23
37	1	0	19	13	3	46
38	1	0	28	15	4	33
39	1	0	15	10	6	43
40	1	0	2	7	8	10
41	1	0	1	12	10	15
42	1	1	5	6	1	24
43	1	1	29	2	2	20
44	1	1	13	8	3	41
45	1	1	2	8	4	11
46	1	1	9	11	5	14
47	1	1	27	12	6	15
48	1	1	32	8	7	26
49	1	1	26	14	8	47
50	1	1	28	15	9	48
51	1	1	24	7	10	34
52	1	2	3	9	1	12
53	1	2	27	7	2	4
54	1	2	9	11	3	14
55	1	2	20	10	4	43
56	1	2	6	8	5	11
57	1	2	14	13	6	46
58	1	2	5	6	7	39
59	1	2	29	15	9	48
60	1	2	15	4	10	7
61	1	3	27	12	1	45
62	1	3	22	6	5	9
63	1	3	28	15	6	48
64	1	3	15	4	7	22
65	1	3	24	7	9	34
66	1	3	8	13	10	16
67	2	0	26	6	1	9
68	2	0	11	3	2	6
69	2	0	9	11	5	14
70	2	0	20	10	6	43
71	2	0	2	8	7	41
72	2	0	16	9	8	27
73	2	0	22	6	10	24
74	2	1	22	10	1	28
75	2	1	5	6	2	9
76	2	1	30	12	4	15
77	2	1	32	8	5	26
78	2	1	9	11	6	44
79	2	1	28	7	7	10
80	2	1	4	12	8	45
81	2	1	2	8	10	11
82	2	2	1	12	1	30
83	2	2	2	7	2	25
84	2	2	32	8	3	41
85	2	2	7	10	4	13
86	2	2	26	6	5	39
87	2	2	28	15	6	48
88	2	2	6	6	7	24
89	2	2	9	11	8	29
90	2	2	27	12	10	15
91	2	3	16	9	2	27
92	2	3	9	11	3	29
93	2	3	6	8	4	11
94	2	3	22	9	9	42
95	3	0	16	9	3	12
96	3	0	9	11	4	14
97	3	0	20	10	6	28
98	3	0	13	8	8	26
99	3	1	4	12	1	15
100	3	1	32	8	2	26
101	3	1	9	14	3	32
102	3	1	3	9	4	12
103	3	1	5	7	5	40
104	3	1	29	15	6	33
105	3	1	25	10	8	28
106	3	1	2	4	10	37
107	3	2	22	9	1	27
108	3	2	17	6	2	24
109	3	2	28	15	4	18
110	3	2	32	8	5	11
111	3	2	2	4	7	7
112	3	2	9	11	8	14
113	3	2	31	10	9	28
114	3	2	27	7	10	19
115	3	3	8	7	1	19
116	3	3	2	4	2	22
117	3	3	6	6	7	9
118	3	3	27	12	8	30
119	3	3	9	11	9	14
120	4	0	2	4	2	22
121	4	0	9	13	3	46
122	4	0	32	10	8	28
123	4	0	29	15	9	48
124	4	1	8	13	1	31
125	4	1	18	7	2	10
126	4	1	20	9	3	12
127	4	1	9	11	4	29
128	4	1	4	12	6	45
129	4	1	15	10	7	43
130	4	1	6	8	8	26
131	4	1	16	10	9	28
132	4	2	5	7	1	19
133	4	2	6	8	2	26
134	4	2	4	9	4	12
135	4	2	20	9	5	27
136	4	2	9	11	6	29
137	4	2	19	7	7	34
138	4	2	14	13	8	16
139	4	2	32	8	10	41
140	4	3	16	10	1	43
141	4	3	27	7	5	40
142	4	3	9	11	6	29
143	4	3	11	5	7	8
144	4	3	13	13	8	46
145	4	3	2	4	10	22
\.


--
-- TOC entry 3412 (class 0 OID 61462)
-- Dependencies: 226
-- Data for Name: students; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.students (user_id, group_id) FROM stdin;
49	1
50	1
51	1
52	1
53	1
54	1
55	1
56	1
57	1
58	1
59	1
60	1
61	1
62	1
63	1
64	1
65	1
66	1
67	1
68	1
69	2
70	2
71	2
72	2
73	2
74	2
75	2
76	2
77	2
78	2
79	2
80	2
81	2
82	2
83	2
84	2
85	2
86	2
87	2
88	2
89	2
90	2
91	2
92	2
93	2
94	2
95	2
96	2
97	3
98	3
99	3
100	3
101	3
102	3
103	3
104	3
105	3
106	3
107	3
108	4
109	4
110	4
111	4
112	4
113	4
114	4
115	4
116	4
117	4
118	4
119	4
120	4
121	4
122	4
123	4
124	4
125	4
126	5
127	5
128	5
129	5
130	5
131	5
132	5
133	5
134	5
135	5
136	5
137	5
138	5
139	5
140	5
141	5
142	5
143	5
144	5
145	5
146	5
147	5
148	5
149	5
150	5
151	5
152	5
153	6
154	6
155	6
156	6
157	6
158	6
159	6
160	6
161	6
162	6
163	6
164	6
165	6
166	6
167	6
168	6
169	6
170	6
171	6
172	6
173	6
174	6
175	6
176	6
177	6
178	7
179	7
180	7
181	7
182	7
183	7
184	7
185	7
186	7
187	7
188	7
189	7
190	7
191	7
192	7
193	7
194	7
195	7
196	7
197	7
198	7
199	7
200	7
201	7
202	7
203	7
204	8
205	8
206	8
207	8
208	8
209	8
210	8
211	8
212	8
213	8
214	8
215	8
216	8
217	8
218	8
219	8
220	8
221	8
222	8
223	8
224	8
225	9
226	9
227	9
228	9
229	9
230	9
231	9
232	9
233	9
234	9
235	9
236	9
237	9
238	9
239	9
240	9
241	9
242	9
243	9
244	10
245	10
246	10
247	10
248	10
\.


--
-- TOC entry 3413 (class 0 OID 61467)
-- Dependencies: 227
-- Data for Name: teachers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.teachers (user_id, teach_course_id) FROM stdin;
4	1
5	2
6	3
7	4
8	5
9	6
10	7
11	8
12	9
13	10
14	11
15	12
16	13
17	14
18	15
19	1
20	2
21	3
22	4
23	5
24	6
25	7
26	8
27	9
28	10
29	11
30	12
31	13
32	14
33	15
34	1
35	2
36	3
37	4
38	5
39	6
40	7
41	8
42	9
43	10
44	11
45	12
46	13
47	14
48	15
\.


--
-- TOC entry 3415 (class 0 OID 61473)
-- Dependencies: 229
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, first_name, last_name, password, role, username) FROM stdin;
1	James	Martin	lvfy9A@r	2	James.Martin1@gmail.com
2	Liam	Garcia	3KCmzkVE	2	Liam.Garcia2@gmail.com
3	James	Smith	nZfWS1G1	2	James.Smith3@gmail.com
4	Michael	Robinson	jCm1^B29	1	Michael.Robinson4@gmail.com
5	James	White	4H0^iZIA	1	James.White5@gmail.com
6	William	Martinez	e#eXbyKc	1	William.Martinez6@gmail.com
7	Robert	Harris	3j7&Qh@E	1	Robert.Harris7@gmail.com
8	Ava	Davis	YE1$x1_b	1	Ava.Davis8@gmail.com
9	Daniel	White	oV5lo^DU	1	Daniel.White9@gmail.com
10	Amelia	Jones	x_RgQhPz	1	Amelia.Jones10@gmail.com
11	Emma	Jones	GHssKmq2	1	Emma.Jones11@gmail.com
12	Amelia	Williams	KtKy#Sl5	1	Amelia.Williams12@gmail.com
13	Emma	Robinson	x_FFhg4S	1	Emma.Robinson13@gmail.com
14	Olivia	Martinez	9lK59rYn	1	Olivia.Martinez14@gmail.com
15	Daniel	Martinez	Offhz@Op	1	Daniel.Martinez15@gmail.com
16	James	Smith	dKu3BSv0	1	James.Smith16@gmail.com
17	William	Taylor	IDSgcetp	1	William.Taylor17@gmail.com
18	Amelia	Thompson	#E2K3a$!	1	Amelia.Thompson18@gmail.com
19	Ava	Jackson	^nf40OIG	1	Ava.Jackson19@gmail.com
20	Michael	Harris	!EvfAdGm	1	Michael.Harris20@gmail.com
21	Robert	Harris	4XWVXtwj	1	Robert.Harris21@gmail.com
22	Robert	Harris	hGedUXao	1	Robert.Harris22@gmail.com
23	Olivia	Taylor	QXM04quv	1	Olivia.Taylor23@gmail.com
24	David	Thomas	!zCLyMtU	1	David.Thomas24@gmail.com
25	Matthew	Johnson	Z2LJCwjm	1	Matthew.Johnson25@gmail.com
26	Amelia	Robinson	J7dsbRHd	1	Amelia.Robinson26@gmail.com
27	Charlotte	Thompson	JDkT6P2M	1	Charlotte.Thompson27@gmail.com
28	John	Garcia	UNnSspXJ	1	John.Garcia28@gmail.com
29	Ava	Davis	iH0F8Ieo	1	Ava.Davis29@gmail.com
30	Olivia	Thomas	PRUAwG$I	1	Olivia.Thomas30@gmail.com
31	Isabella	Martin	0v5Q7yrE	1	Isabella.Martin31@gmail.com
32	Ava	Martin	eZ0hahg0	1	Ava.Martin32@gmail.com
33	Emma	Taylor	!y4VDTU%	1	Emma.Taylor33@gmail.com
34	Grace	White	cTNgxg*b	1	Grace.White34@gmail.com
35	William	Jackson	O0&#AMR3	1	William.Jackson35@gmail.com
36	Olivia	Wilson	K4GMvQQc	1	Olivia.Wilson36@gmail.com
37	Emma	Taylor	biRdU&1N	1	Emma.Taylor37@gmail.com
38	Emily	Martinez	nCYcrH&3	1	Emily.Martinez38@gmail.com
39	Mia	Johnson	cNw6%10s	1	Mia.Johnson39@gmail.com
40	Michael	Moore	_k&UJev&	1	Michael.Moore40@gmail.com
41	Robert	Wilson	&1MKSJtn	1	Robert.Wilson41@gmail.com
42	Grace	Martin	Uno$M#Zx	1	Grace.Martin42@gmail.com
43	William	White	8BQ^Tu_e	1	William.White43@gmail.com
44	Sophia	Davis	FNKPAHST	1	Sophia.Davis44@gmail.com
45	Grace	Martinez	_iYqR1r1	1	Grace.Martinez45@gmail.com
46	Emma	Harris	H&wKL%5e	1	Emma.Harris46@gmail.com
47	David	Smith	DWY9Hdmi	1	David.Smith47@gmail.com
48	James	Martin	_nzOB77c	1	James.Martin48@gmail.com
49	Joseph	Martin	wdb9Xlng	0	Joseph.Martin49@gmail.com
50	Isabella	Thomas	tcg4A*bm	0	Isabella.Thomas50@gmail.com
51	Isabella	Brown	s0E5ehx2	0	Isabella.Brown51@gmail.com
52	Emily	Robinson	gG_LI$FJ	0	Emily.Robinson52@gmail.com
53	Daniel	Thompson	rbWyu$5j	0	Daniel.Thompson53@gmail.com
54	Joseph	Anderson	hHyxatZO	0	Joseph.Anderson54@gmail.com
55	John	Williams	iH@@xSZ*	0	John.Williams55@gmail.com
56	James	Brown	GRTErqzU	0	James.Brown56@gmail.com
57	Grace	White	AZ$R7$r6	0	Grace.White57@gmail.com
58	Grace	Smith	W%kP0!T#	0	Grace.Smith58@gmail.com
59	Emma	Jackson	icK*kWxQ	0	Emma.Jackson59@gmail.com
60	Emma	Davis	2!k&FUnD	0	Emma.Davis60@gmail.com
61	Michael	Miller	Td_n6QXd	0	Michael.Miller61@gmail.com
62	Matthew	Jones	Hj1yvFx1	0	Matthew.Jones62@gmail.com
63	Emily	Moore	Fvbg%Toi	0	Emily.Moore63@gmail.com
64	Matthew	Williams	vl^5widJ	0	Matthew.Williams64@gmail.com
65	Emily	Taylor	CgryrR$W	0	Emily.Taylor65@gmail.com
66	Robert	Robinson	U0pFmihe	0	Robert.Robinson66@gmail.com
67	Isabella	Miller	b44Ek0m#	0	Isabella.Miller67@gmail.com
68	Ava	Harris	ZDoTN7@n	0	Ava.Harris68@gmail.com
69	Matthew	Jackson	L44GX0gM	0	Matthew.Jackson69@gmail.com
70	Ava	Martin	InB%LL5_	0	Ava.Martin70@gmail.com
71	Isabella	Thomas	7Ot3zIrm	0	Isabella.Thomas71@gmail.com
72	Isabella	Thompson	oFhnROS^	0	Isabella.Thompson72@gmail.com
73	Daniel	Jackson	3C0Mv89c	0	Daniel.Jackson73@gmail.com
74	Grace	Brown	S%#usDJ*	0	Grace.Brown74@gmail.com
75	Sophia	Miller	XDev09KF	0	Sophia.Miller75@gmail.com
76	David	Wilson	3SLeq#sa	0	David.Wilson76@gmail.com
77	John	Garcia	Mx9*REHi	0	John.Garcia77@gmail.com
78	Joseph	Anderson	k_jroZ9H	0	Joseph.Anderson78@gmail.com
79	Charlotte	Davis	lgkFiSjs	0	Charlotte.Davis79@gmail.com
80	Mia	Williams	S0l8k78b	0	Mia.Williams80@gmail.com
81	Liam	Thompson	n@*lwoGC	0	Liam.Thompson81@gmail.com
82	David	Taylor	AiguJZAS	0	David.Taylor82@gmail.com
83	Amelia	Jackson	kfZd&V#z	0	Amelia.Jackson83@gmail.com
84	Michael	White	EXOo!zG*	0	Michael.White84@gmail.com
85	William	Thompson	g@QL^!ns	0	William.Thompson85@gmail.com
86	John	Davis	%pC@4A7Z	0	John.Davis86@gmail.com
87	Grace	White	2oF2zntG	0	Grace.White87@gmail.com
88	Joseph	Davis	qvUeZaYh	0	Joseph.Davis88@gmail.com
89	Amelia	Thompson	Dl_zd8TH	0	Amelia.Thompson89@gmail.com
90	Olivia	Garcia	HJigz3Dz	0	Olivia.Garcia90@gmail.com
91	Sophia	Williams	3%LKnrwq	0	Sophia.Williams91@gmail.com
92	Mia	Thomas	5wzM0iA7	0	Mia.Thomas92@gmail.com
93	Charlotte	Smith	Yl7@XPBj	0	Charlotte.Smith93@gmail.com
94	Daniel	Brown	WPdsTSnB	0	Daniel.Brown94@gmail.com
95	Robert	Davis	J&c9JhvA	0	Robert.Davis95@gmail.com
96	Emma	Johnson	EyCNBQ#D	0	Emma.Johnson96@gmail.com
97	Grace	Martin	@ACsvO$m	0	Grace.Martin97@gmail.com
98	Sophia	Harris	r#2MkIGe	0	Sophia.Harris98@gmail.com
99	Mia	Robinson	9Z&CUm!m	0	Mia.Robinson99@gmail.com
100	Emma	Jones	WutC6v#f	0	Emma.Jones100@gmail.com
101	Matthew	Davis	#^HR69pv	0	Matthew.Davis101@gmail.com
102	John	Jones	4yKYC_Mw	0	John.Jones102@gmail.com
103	Olivia	Jackson	L2q%yt*8	0	Olivia.Jackson103@gmail.com
104	James	Anderson	iaa!V6Mj	0	James.Anderson104@gmail.com
105	Michael	Miller	Lz3YeYN_	0	Michael.Miller105@gmail.com
106	Joseph	Smith	_MzmPyVf	0	Joseph.Smith106@gmail.com
107	Mia	Smith	ZZGoLb2y	0	Mia.Smith107@gmail.com
108	Mia	Moore	4na#w$m%	0	Mia.Moore108@gmail.com
109	Sophia	Davis	JSHlLr#q	0	Sophia.Davis109@gmail.com
110	William	Martin	OKOky%Te	0	William.Martin110@gmail.com
111	Matthew	White	vjfxcwdW	0	Matthew.White111@gmail.com
112	Liam	Harris	uFtbnPAg	0	Liam.Harris112@gmail.com
113	Liam	White	lwwM0KuY	0	Liam.White113@gmail.com
114	James	Martin	y&RHxlzs	0	James.Martin114@gmail.com
115	James	Miller	bVVo698F	0	James.Miller115@gmail.com
116	Sophia	Thompson	Sw46AxBY	0	Sophia.Thompson116@gmail.com
117	Liam	Thomas	vBlMxEH5	0	Liam.Thomas117@gmail.com
118	Emily	Johnson	3Th$9PjX	0	Emily.Johnson118@gmail.com
119	Robert	Garcia	8lNPY6Mb	0	Robert.Garcia119@gmail.com
120	Mia	Johnson	3qnU7$5*	0	Mia.Johnson120@gmail.com
121	Robert	Harris	wKTt_gts	0	Robert.Harris121@gmail.com
122	Amelia	Smith	rJXt*AJS	0	Amelia.Smith122@gmail.com
123	Matthew	Taylor	FOE@W7rv	0	Matthew.Taylor123@gmail.com
124	Ava	Martin	URzR@Y9z	0	Ava.Martin124@gmail.com
125	Michael	White	LXrYyxau	0	Michael.White125@gmail.com
126	James	Taylor	O*WmL_v2	0	James.Taylor126@gmail.com
127	Ava	Thompson	bGqZ$_^5	0	Ava.Thompson127@gmail.com
128	Emma	Anderson	8oIt6X!c	0	Emma.Anderson128@gmail.com
129	Olivia	Thompson	1^nynpxo	0	Olivia.Thompson129@gmail.com
130	Emily	Martin	^YleU60D	0	Emily.Martin130@gmail.com
131	Emma	Garcia	mv*KsFZY	0	Emma.Garcia131@gmail.com
132	Emily	Miller	PY^1um!_	0	Emily.Miller132@gmail.com
133	Charlotte	Harris	OYDJV8Mg	0	Charlotte.Harris133@gmail.com
134	Emily	Wilson	#H6KS4e$	0	Emily.Wilson134@gmail.com
135	Isabella	Martin	#ScG_Kcg	0	Isabella.Martin135@gmail.com
136	Sophia	Moore	*x0$&z&j	0	Sophia.Moore136@gmail.com
137	Mia	Martin	jID9b#Hv	0	Mia.Martin137@gmail.com
138	Robert	Smith	YO0pRIjT	0	Robert.Smith138@gmail.com
139	Sophia	Jackson	dWrXjSe%	0	Sophia.Jackson139@gmail.com
140	Isabella	Smith	1_pWc9kY	0	Isabella.Smith140@gmail.com
141	Olivia	Thompson	fcW4zaeN	0	Olivia.Thompson141@gmail.com
142	Emily	Thompson	v2nJ4T2h	0	Emily.Thompson142@gmail.com
143	Emily	Williams	mRknB&o@	0	Emily.Williams143@gmail.com
144	Grace	Davis	#8FPy!LW	0	Grace.Davis144@gmail.com
145	Amelia	Harris	RJDikZnY	0	Amelia.Harris145@gmail.com
146	Matthew	Taylor	t1RQ_Ws_	0	Matthew.Taylor146@gmail.com
147	Michael	Garcia	a*Hx*is&	0	Michael.Garcia147@gmail.com
148	Amelia	Wilson	u13!FlYi	0	Amelia.Wilson148@gmail.com
149	Sophia	Miller	DGK$aP9v	0	Sophia.Miller149@gmail.com
150	Isabella	Garcia	_t7a&F@_	0	Isabella.Garcia150@gmail.com
151	Daniel	Harris	vM%3MJTz	0	Daniel.Harris151@gmail.com
152	Emily	Jones	Jaqf8xjI	0	Emily.Jones152@gmail.com
153	Matthew	Robinson	w4@beeZs	0	Matthew.Robinson153@gmail.com
154	John	Williams	%swVxcCd	0	John.Williams154@gmail.com
155	Daniel	Johnson	kX7dikcV	0	Daniel.Johnson155@gmail.com
156	Charlotte	Jones	qBi^Voi^	0	Charlotte.Jones156@gmail.com
157	Sophia	Miller	RGLj8V*s	0	Sophia.Miller157@gmail.com
158	Matthew	Anderson	PVvShEnN	0	Matthew.Anderson158@gmail.com
159	Joseph	Thompson	4D5dIMyd	0	Joseph.Thompson159@gmail.com
160	Sophia	Jones	LEXjH_Bg	0	Sophia.Jones160@gmail.com
161	Robert	Brown	rxeK5^er	0	Robert.Brown161@gmail.com
162	Isabella	Moore	#O^FXaqB	0	Isabella.Moore162@gmail.com
163	John	Garcia	M@tsbue2	0	John.Garcia163@gmail.com
164	Michael	Jackson	Lm$fgbF@	0	Michael.Jackson164@gmail.com
165	Matthew	Martin	NQyQSwAz	0	Matthew.Martin165@gmail.com
166	Daniel	Jackson	aKh938R@	0	Daniel.Jackson166@gmail.com
167	David	Martin	lCPydF!y	0	David.Martin167@gmail.com
168	James	Jackson	5f!*ZD_T	0	James.Jackson168@gmail.com
169	Emma	Johnson	BKCTz7T_	0	Emma.Johnson169@gmail.com
170	Olivia	Harris	J53Ko9*@	0	Olivia.Harris170@gmail.com
171	Grace	Johnson	weDZt@jD	0	Grace.Johnson171@gmail.com
172	William	Wilson	zeNFA0A$	0	William.Wilson172@gmail.com
173	Emily	Miller	_X32IPNr	0	Emily.Miller173@gmail.com
174	Emily	Williams	HGp5%Y$8	0	Emily.Williams174@gmail.com
175	Robert	Garcia	puxdCpID	0	Robert.Garcia175@gmail.com
176	Matthew	Thomas	wFvEpkOY	0	Matthew.Thomas176@gmail.com
177	Emma	Williams	!!GzlcGU	0	Emma.Williams177@gmail.com
178	Robert	Taylor	uz#oq5wm	0	Robert.Taylor178@gmail.com
179	James	Williams	!jhWWLdd	0	James.Williams179@gmail.com
180	John	Brown	xcRf2l&8	0	John.Brown180@gmail.com
181	James	Davis	5SNsM8XC	0	James.Davis181@gmail.com
182	John	Miller	Pl6Wgkvw	0	John.Miller182@gmail.com
183	Emily	Garcia	PEyT8yo%	0	Emily.Garcia183@gmail.com
184	Olivia	Taylor	1WwEjDhB	0	Olivia.Taylor184@gmail.com
185	Emma	Brown	NOaWRrhR	0	Emma.Brown185@gmail.com
186	Michael	Miller	0butd6k3	0	Michael.Miller186@gmail.com
187	David	Wilson	%^VGvXbE	0	David.Wilson187@gmail.com
188	Amelia	Brown	DdEE$8Pl	0	Amelia.Brown188@gmail.com
189	David	Miller	hUbXaNKc	0	David.Miller189@gmail.com
190	Matthew	Brown	dpfs0Y!B	0	Matthew.Brown190@gmail.com
191	James	Johnson	H42um#a2	0	James.Johnson191@gmail.com
192	Amelia	Harris	H5^BSatv	0	Amelia.Harris192@gmail.com
193	Olivia	Smith	RrvqVppc	0	Olivia.Smith193@gmail.com
194	Amelia	Davis	%uSBChY%	0	Amelia.Davis194@gmail.com
195	Joseph	Wilson	FKv1i6K7	0	Joseph.Wilson195@gmail.com
196	Grace	Jackson	NSj%QzsF	0	Grace.Jackson196@gmail.com
197	Daniel	Wilson	a!SlTH0y	0	Daniel.Wilson197@gmail.com
198	Sophia	White	ZI2acbUh	0	Sophia.White198@gmail.com
199	Michael	Taylor	sCVh@9ac	0	Michael.Taylor199@gmail.com
200	Matthew	Harris	6Z4rZyaC	0	Matthew.Harris200@gmail.com
201	James	Miller	KdX4xotS	0	James.Miller201@gmail.com
202	Robert	Thomas	LnXOfX9e	0	Robert.Thomas202@gmail.com
203	Daniel	Moore	qUtrBHuQ	0	Daniel.Moore203@gmail.com
204	Amelia	Moore	NgQIyz$N	0	Amelia.Moore204@gmail.com
205	Charlotte	White	hbTMRAXX	0	Charlotte.White205@gmail.com
206	Isabella	Johnson	Ixeq!E1x	0	Isabella.Johnson206@gmail.com
207	Liam	Garcia	*%Hs3GPA	0	Liam.Garcia207@gmail.com
208	Emily	Thomas	NVKso&x!	0	Emily.Thomas208@gmail.com
209	Sophia	Williams	bTjvYN1L	0	Sophia.Williams209@gmail.com
210	Matthew	Taylor	cB98t8Az	0	Matthew.Taylor210@gmail.com
211	Mia	Moore	R7i_2vkv	0	Mia.Moore211@gmail.com
212	Isabella	Jackson	Z0xy%AE@	0	Isabella.Jackson212@gmail.com
213	Matthew	Wilson	jiWLvmjX	0	Matthew.Wilson213@gmail.com
214	Michael	Thompson	sx2kns#l	0	Michael.Thompson214@gmail.com
215	Amelia	Miller	xii5U7IQ	0	Amelia.Miller215@gmail.com
216	Isabella	Smith	89qk2dld	0	Isabella.Smith216@gmail.com
217	Mia	Johnson	JN6B$xdU	0	Mia.Johnson217@gmail.com
218	Liam	Williams	q3_gqGTH	0	Liam.Williams218@gmail.com
219	Olivia	Wilson	gtA3_zCc	0	Olivia.Wilson219@gmail.com
220	William	Johnson	3n9xCLPc	0	William.Johnson220@gmail.com
221	Sophia	Williams	5qyC9#iT	0	Sophia.Williams221@gmail.com
222	Amelia	Thomas	^q3L&MHS	0	Amelia.Thomas222@gmail.com
223	Amelia	Garcia	s#XA_iM@	0	Amelia.Garcia223@gmail.com
224	Emily	Robinson	BO%!q06I	0	Emily.Robinson224@gmail.com
225	John	Brown	Webqojae	0	John.Brown225@gmail.com
226	Amelia	Wilson	kGQyKlhp	0	Amelia.Wilson226@gmail.com
227	Isabella	Miller	AKzsWvk@	0	Isabella.Miller227@gmail.com
228	Charlotte	Wilson	Vo9^YRFz	0	Charlotte.Wilson228@gmail.com
229	Daniel	Thomas	kR0GU3gE	0	Daniel.Thomas229@gmail.com
230	Isabella	Jones	BcqWuj%y	0	Isabella.Jones230@gmail.com
231	James	Davis	K^7gRF1h	0	James.Davis231@gmail.com
232	Charlotte	Thomas	&GaKiWIX	0	Charlotte.Thomas232@gmail.com
233	Emily	Anderson	cHYAskE0	0	Emily.Anderson233@gmail.com
234	Sophia	Harris	BIcZ&^tl	0	Sophia.Harris234@gmail.com
235	Emily	Taylor	WgxkHWp6	0	Emily.Taylor235@gmail.com
236	James	Wilson	zOp7A!fe	0	James.Wilson236@gmail.com
237	Daniel	Martinez	m4j&vWYy	0	Daniel.Martinez237@gmail.com
238	Charlotte	Brown	XGEU4L@O	0	Charlotte.Brown238@gmail.com
239	Charlotte	Jones	NcB7qPRK	0	Charlotte.Jones239@gmail.com
240	Emma	Williams	lo$72wtb	0	Emma.Williams240@gmail.com
241	Daniel	Garcia	wt_3G111	0	Daniel.Garcia241@gmail.com
242	Matthew	Davis	zF!2^eLb	0	Matthew.Davis242@gmail.com
243	Robert	Jackson	6rDR92uD	0	Robert.Jackson243@gmail.com
244	Mia	Miller	2x*J59M4	0	Mia.Miller244@gmail.com
245	Emily	Jackson	C1JU2&LG	0	Emily.Jackson245@gmail.com
246	Charlotte	Harris	$fO1BMOM	0	Charlotte.Harris246@gmail.com
247	Joseph	Garcia	ifQcQ_3i	0	Joseph.Garcia247@gmail.com
248	Mia	Robinson	lEF2ocsU	0	Mia.Robinson248@gmail.com
\.


--
-- TOC entry 3426 (class 0 OID 0)
-- Dependencies: 216
-- Name: classrooms_classroom_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.classrooms_classroom_id_seq', 35, true);


--
-- TOC entry 3427 (class 0 OID 0)
-- Dependencies: 219
-- Name: courses_course_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.courses_course_id_seq', 15, true);


--
-- TOC entry 3428 (class 0 OID 0)
-- Dependencies: 221
-- Name: groups_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.groups_group_id_seq', 10, true);


--
-- TOC entry 3429 (class 0 OID 0)
-- Dependencies: 224
-- Name: lessons_lesson_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.lessons_lesson_id_seq', 145, true);


--
-- TOC entry 3430 (class 0 OID 0)
-- Dependencies: 228
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 248, true);


--
-- TOC entry 3230 (class 2606 OID 61421)
-- Name: admins admins_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admins
    ADD CONSTRAINT admins_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3232 (class 2606 OID 61430)
-- Name: classrooms classrooms_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.classrooms
    ADD CONSTRAINT classrooms_pkey PRIMARY KEY (classroom_id);


--
-- TOC entry 3234 (class 2606 OID 61442)
-- Name: courses courses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_pkey PRIMARY KEY (course_id);


--
-- TOC entry 3227 (class 2606 OID 61415)
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- TOC entry 3236 (class 2606 OID 61449)
-- Name: groups groups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (group_id);


--
-- TOC entry 3238 (class 2606 OID 61461)
-- Name: lessons lessons_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT lessons_pkey PRIMARY KEY (lesson_id);


--
-- TOC entry 3240 (class 2606 OID 61466)
-- Name: students students_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.students
    ADD CONSTRAINT students_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3242 (class 2606 OID 61471)
-- Name: teachers teachers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teachers
    ADD CONSTRAINT teachers_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3244 (class 2606 OID 61481)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3228 (class 1259 OID 61416)
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- TOC entry 3250 (class 2606 OID 61512)
-- Name: lessons fk17ucc7gjfjddsyi0gvstkqeat; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT fk17ucc7gjfjddsyi0gvstkqeat FOREIGN KEY (course_id) REFERENCES public.courses(course_id);


--
-- TOC entry 3248 (class 2606 OID 61497)
-- Name: groups_courses fk68xnkbo7urhqormcaa50dcxuw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups_courses
    ADD CONSTRAINT fk68xnkbo7urhqormcaa50dcxuw FOREIGN KEY (course_id) REFERENCES public.courses(course_id);


--
-- TOC entry 3249 (class 2606 OID 61502)
-- Name: groups_courses fkb5nvrwxye8n0ct77q8s3war1x; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups_courses
    ADD CONSTRAINT fkb5nvrwxye8n0ct77q8s3war1x FOREIGN KEY (group_id) REFERENCES public.groups(group_id);


--
-- TOC entry 3256 (class 2606 OID 61542)
-- Name: teachers fkb8dct7w2j1vl1r2bpstw5isc0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teachers
    ADD CONSTRAINT fkb8dct7w2j1vl1r2bpstw5isc0 FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 3251 (class 2606 OID 61507)
-- Name: lessons fkbffxqtymudjwdb39m7dnjn4ey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT fkbffxqtymudjwdb39m7dnjn4ey FOREIGN KEY (classroom_id) REFERENCES public.classrooms(classroom_id);


--
-- TOC entry 3252 (class 2606 OID 61522)
-- Name: lessons fkbr76cuebuufbbltujpfq04tto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT fkbr76cuebuufbbltujpfq04tto FOREIGN KEY (teacher_id) REFERENCES public.teachers(user_id);


--
-- TOC entry 3254 (class 2606 OID 61532)
-- Name: students fkdt1cjx5ve5bdabmuuf3ibrwaq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.students
    ADD CONSTRAINT fkdt1cjx5ve5bdabmuuf3ibrwaq FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 3245 (class 2606 OID 61482)
-- Name: admins fkgc8dtql9mkq268detxiox7fpm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admins
    ADD CONSTRAINT fkgc8dtql9mkq268detxiox7fpm FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 3257 (class 2606 OID 61537)
-- Name: teachers fkk805y9f18ppaml2ng1c1fhe7n; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teachers
    ADD CONSTRAINT fkk805y9f18ppaml2ng1c1fhe7n FOREIGN KEY (teach_course_id) REFERENCES public.courses(course_id);


--
-- TOC entry 3246 (class 2606 OID 61492)
-- Name: classrooms_courses fkk8rbwp31oatef2ohykhr6b6hq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.classrooms_courses
    ADD CONSTRAINT fkk8rbwp31oatef2ohykhr6b6hq FOREIGN KEY (classroom_id) REFERENCES public.classrooms(classroom_id);


--
-- TOC entry 3255 (class 2606 OID 61527)
-- Name: students fkmsev1nou0j86spuk5jrv19mss; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.students
    ADD CONSTRAINT fkmsev1nou0j86spuk5jrv19mss FOREIGN KEY (group_id) REFERENCES public.groups(group_id);


--
-- TOC entry 3247 (class 2606 OID 61487)
-- Name: classrooms_courses fkr33hf7ya3mj3l45uk13st6ufk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.classrooms_courses
    ADD CONSTRAINT fkr33hf7ya3mj3l45uk13st6ufk FOREIGN KEY (course_id) REFERENCES public.courses(course_id);


--
-- TOC entry 3253 (class 2606 OID 61517)
-- Name: lessons fktdolsaotaqlwxbxwaxt00kimk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT fktdolsaotaqlwxbxwaxt00kimk FOREIGN KEY (group_id) REFERENCES public.groups(group_id);


-- Completed on 2023-10-18 11:49:25

--
-- PostgreSQL database dump complete
--

