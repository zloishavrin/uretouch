export const SVGSelector = ({ type, className }) => {

    switch (type) {
        case 'logo':
            return (
                <svg className={className} viewBox="0 0 170 170" fill="none">
                    <defs>
                        <clipPath id="clip1_27">
                            <rect id="Frame 2 1" width="170.000000" height="170.000000" fill="white" fillOpacity="0"/>
                        </clipPath>
                    </defs>
                    <rect id="Frame 2 1" width="170.000000" height="170.000000" fill="#FFFFFF" fillOpacity="0"/>
                    <g clipPath="url(#clip1_27)">
                        <path id="Vector" d="M86 158.06C45.09 158.06 11.93 124.9 11.93 84C11.93 43.09 45.09 9.93 86 9.93C126.9 9.93 160.06 43.09 160.06 84C160.06 124.9 126.9 158.06 86 158.06Z" stroke="#FFC42C" strokeOpacity="1.000000" strokeWidth="11.000000"/>
                        <path id="Vector" d="M45.71 38.97L45.71 110.3L61.5 123.81C65.44 127.18 70.45 129.03 75.62 129.03L99.62 129.03" stroke="#FFC42C" strokeOpacity="1.000000" strokeWidth="11.000000"/>
                        <path id="Vector" d="M126.29 129.03L126.29 57.69L110.49 44.18C106.55 40.81 101.55 38.97 96.37 38.97L72.37 38.97" stroke="#FFC42C" strokeOpacity="1.000000" strokeWidth="11.000000"/>
                    </g>
                </svg>
            );
        case 'back':
            return (
                <svg viewBox="0 0 240.823 240.823" className={className}>
                    <g>
                        <path
                            id="Chevron_Right"
                            d="M57.633,129.007L165.93,237.268c4.752,4.74,12.451,4.74,17.215,0c4.752-4.74,4.752-12.439,0-17.179 l-99.707-99.671l99.695-99.671c4.752-4.74,4.752-12.439,0-17.191c-4.752-4.74-12.463-4.74-17.215,0L57.621,111.816 C52.942,116.507,52.942,124.327,57.633,129.007z"
                            fill="#ffffff"
                        ></path>
                    </g>
                </svg>
            )
        default:
            return ''
    }

}